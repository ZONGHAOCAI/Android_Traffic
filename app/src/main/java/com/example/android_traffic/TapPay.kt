package com.example.android_traffic

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.gms.wallet.PaymentData
import com.google.android.gms.wallet.TransactionInfo
import com.google.android.gms.wallet.WalletConstants
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.runBlocking
import tech.cherri.tpdirect.api.*

interface GetPrimeCallback { //
    fun onGetPrimeResult(result: Boolean)
}

class TapPay {
    private val requestCode = 101
    private lateinit var tpdGooglePay: TPDGooglePay
    private var id = 0
    private var price = 0
    private var phone = ""
    private var vehicleNo = ""

    // 測試環境網址
    private val sandbox = "https://sandbox.tappaysdk.com/"

    // 取得Prime後跟TapPay確定支付的連線網址
    private val primeUrl = "tpc/payment/pay-by-prime"

    // 信用卡種類
    private val cardTypes = arrayOf(
        TPDCard.CardType.Visa,
        TPDCard.CardType.MasterCard,
        TPDCard.CardType.JCB,
        TPDCard.CardType.AmericanExpress
    )

    companion object {
        private var instance: TapPay? = null

        @Synchronized
        fun getInstance(): TapPay {
            if (instance == null) {
                instance = TapPay()
            }
            return instance!!
        }
    }

    fun prepareGooglePay(context: Context, id: Int, price: Int,phone:String,vehicleNo:String) {
        this.id = id
        this.price = price
        this.phone = phone
        this.vehicleNo = vehicleNo
        TPDSetup.initInstance(
            context,
            context.getString(R.string.TapPay_AppID).toInt(),
            context.getString(R.string.TapPay_AppKey),
            TPDServerType.Sandbox
        )
        // 建立商店物件
        val tpdMerchant = TPDMerchant()
        // 設定商店名稱
        tpdMerchant.merchantName = context.getString(R.string.TapPay_MerchantName)
        // 設定允許的信用卡種類
        tpdMerchant.supportedNetworks = cardTypes
        // 建立消費者物件，並設定需要填寫項目
        val tpdConsumer = TPDConsumer()
        // 不需要電話號碼
        tpdConsumer.isPhoneNumberRequired = false
        // 不需要運送地址
        tpdConsumer.isShippingAddressRequired = false
        // 不需要Email
        tpdConsumer.isEmailRequired = false
        tpdGooglePay = TPDGooglePay(context as Activity?, tpdMerchant, tpdConsumer)
        // 檢查使用者裝置是否支援Google Pay
        tpdGooglePay.isGooglePayAvailable { isReadyToPay, message ->
            if (isReadyToPay) {
                // 跳出user資訊視窗讓user確認，確認後會呼叫onActivityResult()
                tpdGooglePay.requestPayment(
                    TransactionInfo.newBuilder()
                        .setTotalPriceStatus(WalletConstants.TOTAL_PRICE_STATUS_FINAL)
                        .setTotalPrice("${this.price}") // 消費總金額
                        .setCurrencyCode("TWD") // 設定幣別
                        .build(), requestCode
                )
            }
        }
    }

    fun getPrimeFromTapPay(paymentData: PaymentData, context: Context, callback: GetPrimeCallback) {
        /* 呼叫getPrime()只將支付資料提交給TapPay以取得prime (代替卡片的一次性字串，此字串的時效為 30 秒)，
            參看https://docs.tappaysdk.com/google-pay/zh/reference.html#prime */
        /* 一般而言，手機提交支付、信用卡資料給TapPay後，TapPay會將信用卡等資訊送至Bank確認是否合法，
               Bank會回一個暫時編號給TapPay方便後續支付確認，TapPay儲存該編號後再回一個自編prime給手機，
               手機再傳給app server，app server再呼叫payByPrime方法提交給TapPay，以確認這筆訂單，
               此時app server就可發訊息告訴手機用戶訂單成立。
               參看圖示 https://docs.tappaysdk.com/google-pay/zh/home.html#home 向下捲動即可看到 */
        tpdGooglePay.getPrime(
            paymentData,
            { prime, _, _ ->
                /* 手機得到prime後，一般會傳給商家app server端儲存在DB後再傳給TapPay，以確認這筆訂單。
                   現在為了方便，手機直接提交給TapPay。
                   得到的結果為JSON，其中"msg":"Success"代表支付成功 */
                val resultJson = generatePayByPrimeForSandBox(
                    prime,
                    context.getString(R.string.TapPay_PartnerKey),
                    context.getString(R.string.TapPay_MerchantID)
                )
                val jsonObject = Gson().fromJson(resultJson, JsonObject::class.java)
                val result = jsonObject["msg"].asString == "Success"
                callback.onGetPrimeResult(result)
                val text = "支付結束，TapPay回應的結果訊息:\n$resultJson"
                Log.d("a", text)
            }
        ) { status: Int, reportMsg: String ->
            val text =
                "TapPay getPrime failed. status: $status, message: $reportMsg"
            Log.d("b", text)
            callback.onGetPrimeResult(false)
        }
    }

    // 將交易資訊送至TapPay測試區
    private fun generatePayByPrimeForSandBox(
        prime: String,
        partnerKey: String,
        merchantId: String
    ): String {
        val paymentJO = JsonObject()
        paymentJO.addProperty("partner_key", partnerKey)
        paymentJO.addProperty("prime", prime)
        paymentJO.addProperty("merchant_id", merchantId)
        paymentJO.addProperty("amount", this.price)
        paymentJO.addProperty("currency", "TWD")
        paymentJO.addProperty("order_number", "$id")
        paymentJO.addProperty("details", "繳納罰緩")
        val cardHolderJO = JsonObject()
        cardHolderJO.addProperty("name", "陳展")
        cardHolderJO.addProperty("phone_number", "+8860927782072")
        cardHolderJO.addProperty("email", "749ka@gmail.com")
        paymentJO.add("cardholder", cardHolderJO)

        // TapPay測試區網址
        val url = sandbox + primeUrl
        var result: String
        // 請求屬性要加上content-type與x-api-key設定，否則錯誤
        runBlocking {
            result = WebRequest().payResult(url, paymentJO.toString(), partnerKey)
        }
        return result
    }
}