package com.example.android_traffic.core.model

import java.io.Serializable
import java.sql.Timestamp


    /**
     * 罰單資料用
     * @param ticketNo 罰單編號
     * @param memID 會員號碼
     * @param driver 違規駕駛(行為)人
     * @param phoneNo 電話
     * @param vehicleNo 車號
     * @param vehicleType 車輛種類
     * @param violationsTime 違規時間
     * @param violationsAddress 違規地點
     * @param violations 違規事項
     * @param reportingRegulations 舉發條例
     * @param amount 違規金額
     * @param paymentDueDate 繳納到期日
     * @param police 員警
     * @param unit 單位
     * @param status 0=未繳納、1=已繳納、2=申訴中、3=申訴成功
     * @param paymentTime 繳納時間
     * @param appendix 附件列表
     */
    //主要建構式
data class Ticket(
    var ticketNo: String? = null,
    var memID: Int? = null,
    var driver: String? = null,
    var phoneNo: String? = null,
    var vehicleNo: String? = null,
    var vehicleType: String? = null,
    var violationsTime: Timestamp? = null,
    var violationsAddress: String? = null,
    var violations: String? = null,
    var reportingRegulations: String? = null,
    var amount: Int? = null,
    var paymentDueDate: Timestamp? = null,
    var police: String? = null,
    var unit: String? = null,
    var status: Int? = null,
    var paymentTime: Timestamp? = null,
    var appendix: List<ByteArray>? = null
) : Serializable