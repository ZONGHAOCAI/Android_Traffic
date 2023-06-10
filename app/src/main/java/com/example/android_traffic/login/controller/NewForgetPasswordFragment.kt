package com.example.android_traffic.login.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.android_traffic.R
import com.example.android_traffic.databinding.FragmentNewForgetPasswordBinding
import com.example.android_traffic.login.viewModel.NewForgetPasswordViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import java.util.concurrent.TimeUnit

class NewForgetPasswordFragment : Fragment() {
    private lateinit var countDownTimer: CountDownTimer
    private val myTag = "TAG_${javaClass.simpleName}"
    private lateinit var binding: FragmentNewForgetPasswordBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var verificationId: String
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    val viewModel: NewForgetPasswordViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().title = getString(R.string.txtForgetPassword)


        binding = FragmentNewForgetPasswordBinding.inflate(inflater, container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            //點擊送出驗證碼
            btSend.setOnClickListener {
                if (mobileValid()) {

                    // 電話號碼格式要符合E.164，要加上country code，台灣為+886
                    //格式正確就呼叫requestVerificationCode（ ）
                    requestVerificationCode("+886${viewModel?.mobile?.value}")
                }

            }
            //按確認按鈕
            // 簡訊傳送的手機與應用程式所在的手機可以不同
            btVerify.setOnClickListener {
                if (verificationCodeValid()) {
                    // 將應用程式收到的驗證ID與使用者輸入的簡訊驗證碼送至Firebase
                    verifyIdAndCode(verificationId, viewModel?.verificationCode?.value!!)
                }
            }
            btResend.setOnClickListener {
                if (mobileValid()) {
                    resendVerificationCode("+886${viewModel?.mobile?.value}", resendToken)
                    startCountDown()
                }

            }
        }
    }

    private fun requestVerificationCode(mobile: String) {
        with(binding) {
            viewModel?.layoutVisible?.value = true
        }
        // 設定簡訊語系為繁體中文
        auth.setLanguageCode("zh-Hant")
        val phoneAuthOptions = PhoneAuthOptions.newBuilder(auth)
            // 電話號碼，驗證碼寄送的電話號碼
            .setPhoneNumber(mobile)
            // 驗證碼失效時間，設為60秒代表即使多次請求驗證碼，過了60秒才會發送第2次
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            // 監控電話驗證的狀態
            .setCallbacks(verifyCallbacks) //callback method
            .build()
        PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions)
    }

    private fun resendVerificationCode(
        phone: String,
        token: PhoneAuthProvider.ForceResendingToken
    ) {
        val phoneAuthOptions = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(verifyCallbacks)
            /* 驗證碼發送後，verifyCallbacks.onCodeSent()會傳來token，
               使用者要求重傳驗證碼必須提供token */
            .setForceResendingToken(token)  //重送必須提供token
            .build()
        PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions)
    }

    private fun verifyIdAndCode(verificationId: String, verificationCode: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId, verificationCode)
        //電話號碼手機驗證PhoneAuthProvider   //暫時的帳密getCredential(verificationId, verificationCode)
        firebaseAuthWithPhoneNumber(credential) //呼叫firebase做驗證
    }

    private fun firebaseAuthWithPhoneNumber(credential: PhoneAuthCredential) {

        auth.signInWithCredential(credential)
            .addOnCompleteListener { task: Task<AuthResult?> ->
                //登入成功的話
                if (task.isSuccessful) {
                    //todo 把電話號碼帶到reset password
                    val bundle = Bundle()
                    val mobilePhone = viewModel?.mobile?.value
                    bundle.putString("mobilePhone", mobilePhone)
                    findNavController().navigate(R.id.resetPasswordFragment, bundle)   //這邊改成resetPassword
                } else {
                    //登入失敗的話
                    with(binding) {
                        val message: String
                        val e = task.exception
                        if (e != null) {
                            message = e.message ?: ""
                            // 使用者輸入的驗證碼與簡訊傳來的不同會產生錯誤
                            if (e is FirebaseAuthInvalidCredentialsException) {
                                etVerificationCode.error = "驗證碼錯誤"
                            }
                        } else {
                            message = "登入失敗"
                        }
                        viewModel?.text?.value = message
                    }
                }
            }
    }

    //實作下面三個方法
    private val verifyCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            /** This callback will be invoked in two situations:
             * 1 - Instant verification. In some cases the phone number can be instantly
             * verified without needing to send or enter a verification code.
             * 2 - Auto-retrieval. On some devices Google Play services can automatically
             * detect the incoming verification SMS and perform verification without
             * user action.  */
            //收到驗證簡訊,把id & code拿去登入
            //但有些手機是簡訊收到就直接拿去登入,會直接呼叫此方法
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d(myTag, "onVerificationCompleted: $credential")
            }

            /**
             * 發送驗證碼填入的電話號碼格式錯誤，或是使用模擬器發送都會產生發送錯誤，
             * 使用模擬器發送會產生下列執行錯誤訊息：
             * App validation failed. Is app running on a physical device?
             */
            //驗證失敗
            override fun onVerificationFailed(e: FirebaseException) {
                Log.e(myTag, "onVerificationFailed: ${e.message}")
            }

            /**
             * The SMS verification code has been sent to the provided phone number,
             * we now need to ask the user to enter the code and then construct a credential
             * by combining the code with a verification ID.
             */
            //驗證碼已經發出去, id是暫時的id, token是重送的時候要用的,沒有要重送的話用不到
            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                Log.d(myTag, "onCodeSent: $id")
                verificationId = id
                resendToken = token
                // 顯示填寫驗證碼版面
                binding.viewModel?.layoutVisible?.value = true  //驗證碼＆手機正確的話,顯示版面
            }
        }



    private fun mobileValid(): Boolean {
        var valid = true
        with(binding) {
            val mobile = viewModel?.mobile?.value?.trim()
            if (mobile == null || mobile.isEmpty()) {
                etMobile.error = "手機號碼不可空白"
                valid = false
            }
        }
        return valid
    }

    private fun verificationCodeValid(): Boolean {
        var valid = true
        with(binding) {
            val verificationCode = viewModel?.verificationCode?.value?.trim()
            if (verificationCode == null || verificationCode.isEmpty()) {
                etVerificationCode.error = "驗證碼不可空白"
                valid = false
            }
        }
        return valid
    }

    override fun onStart() {
        super.onStart()
        // 檢查user是否已經登入，是則FirebaseUser物件不為null
//        auth.currentUser?.let {
//            findNavController().navigate(R.id.resetPasswordFragment)   //todo 看是否可以每次按忘記密碼都轉跳到輸入驗證碼畫面
//        }
    }


    //按下去按鈕會計時
    private fun startCountDown() {
        // 總時長60秒，間隔1秒
        val totalSeconds = 60
        val intervalSeconds = 1

        countDownTimer = object : CountDownTimer(
            (totalSeconds * 1000).toLong(), (intervalSeconds * 1000).toLong()
        ) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = (millisUntilFinished / 1000).toInt()
                binding.btResend.text = secondsRemaining.toString()
            }

            override fun onFinish() {
                binding.btResend.isEnabled = true
                binding.btResend.text = "再次發送驗證信"
            }
        }
        countDownTimer.start()
    }

}