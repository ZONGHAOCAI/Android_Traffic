package com.example.android_traffic.login.controller

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.android_traffic.login.viewModel.ForgetPasswordViewModel
import com.example.android_traffic.R
import com.example.android_traffic.databinding.FragmentForgetPasswordBinding
//import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseOptions
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.io.FileInputStream
import java.util.concurrent.TimeUnit

class ForgetPasswordFragment : Fragment() {
    private lateinit var binding: FragmentForgetPasswordBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var verificationId: String
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForgetPasswordBinding.inflate(inflater, container, false)
        val viewModel: ForgetPasswordViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {

            btnSendConfirmCode.setOnClickListener {
//                requestVerificationCode("+886${viewModel?.login?.value?.phone}")
            }

//            val serviceAccount =
//                FileInputStream("/Users/beckychen/Android/Android_Traffic/app/google-services.json")
//
//            // 初始化 FirebaseApp
//            val firebaseOptions = FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .setDatabaseUrl("https://your-project-id.firebaseio.com")
//                .build()
////
////        FirebaseApp.initializeApp(requireContext(), firebaseOptions.toString())
//
//            // 初始化 FirebaseAuth
//            auth = FirebaseAuth.getInstance()
//
//            //發送驗證碼到用戶手機
//            val phoneNumber = "+1234567890" // 用戶的手機號碼
//            val options = PhoneAuthOptions.newBuilder(auth)
//                .setPhoneNumber(phoneNumber) // Phone number to verify
//                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//
//                .setActivity(requireActivity()) // Activity (for callback binding)
//                .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//
//                    // 驗證碼驗證完成
//                    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//                        // This callback will be invoked in two situations:
//                        // 1 - Instant verification. In some cases the phone number can be instantly
//                        //     verified without needing to send or enter a verification code.
//                        // 2 - Auto-retrieval. On some devices Google Play services can automatically
//                        //     detect the incoming verification SMS and perform verification without
//                        //     user action.
//                        Log.d(TAG, "onVerificationCompleted:$credential")
//                        signInWithPhoneAuthCredential(credential)
//                    }
//
//                    // 驗證碼驗證失敗
//                    override fun onVerificationFailed(e: FirebaseException) {
//                        // This callback is invoked in an invalid request for verification is made,
//                        // for instance if the the phone number format is not valid.
//                        Log.w(TAG, "onVerificationFailed", e)
//
//                        if (e is FirebaseAuthInvalidCredentialsException) {
//                            // Invalid request
//                        } else if (e is FirebaseTooManyRequestsException) {
//                            // The SMS quota for the project has been exceeded
////                        } else if (e is FirebaseAuthMissingActivityForRecaptchaException) {
//                            // reCAPTCHA verification attempted with null Activity
//                        }
//
//                        // Show a message and update the UI
//                    }
//
//                    // 簡訊已發送，等待用戶輸入驗證碼
//                    // 在此處執行相應的操作，例如顯示輸入驗證碼的介面
//                    override fun onCodeSent(
//                        verificationId: String,
//                        token: PhoneAuthProvider.ForceResendingToken,
//                    ) {
//                        // The SMS verification code has been sent to the provided phone number, we
//                        // now need to ask the user to enter the code and then construct a credential
//                        // by combining the code with a verification ID.
//                        Log.d(TAG, "onCodeSent:$verificationId")
//
//                        val verificationCode = "123456" // 用戶輸入的驗證碼
//                        val credential = PhoneAuthProvider.getCredential(verificationId!!, verificationCode)
//
//
//                        // Save verification ID and resending token so we can use them later
////                        storedVerificationId = verificationId
////                        resendToken = token
//                    }
//                }
//                ) // OnVerificationStateChangedCallbacks
//                .build()
//
//
//
//
//            PhoneAuthProvider.verifyPhoneNumber(options)
//
//        }
//
//
//
//
//
////        // 在此處觸發驗證碼簡訊的發送
////        val phoneNumber = "+1234567890" // 用戶的手機號碼
////
////        val options = PhoneAuthOptions.newBuilder(auth)
////            .setPhoneNumber(phoneNumber)
////            .setTimeout(60L, TimeUnit.SECONDS) // 驗證碼有效期限 (選用)
////            .setActivity(requireActivity()) // 可選的 Android Activity (僅適用於 Android)
////            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
////                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
////                    // 驗證碼驗證完成
////                    signInWithPhoneAuthCredential(credential)
////                }
////
////                override fun onVerificationFailed(exception: FirebaseException) {
////                    // 驗證碼驗證失敗
////                    println("驗證碼驗證失敗: ${exception.message}")
////                }
////
////                override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
////                    // 簡訊已發送，等待用戶輸入驗證碼
////                    // 在此處執行相應的操作，例如顯示輸入驗證碼的介面
////                    // 獲取用戶輸入的驗證碼
////                    val verificationCode = "123456" // 用戶輸入的驗證碼
////
////                    // 創建 PhoneAuthCredential 對象
////                    val credential = PhoneAuthProvider.getCredential(verificationId, verificationCode)
////
////                    // 使用該 credential 進行驗證
////                    signInWithPhoneAuthCredential(credential)
////                }
////            })
////            .build()
////
////        PhoneAuthProvider.verifyPhoneNumber(options)
    }

//    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener(requireActivity()) { task ->
//                if (task.isSuccessful) {
//                    // 驗證成功
//                    val user = auth.currentUser
//                    println("用戶驗證成功: $user")
//                } else {
//                    // 驗證失敗
//                    val exception = task.exception
//                    println("驗證失敗: ${exception?.message}")
//                }
//            }
//
//    }



//        private fun requestVerificationCode(mobile: String) {
//            with(binding) {
//                viewModel?.login?.value?.code = true.toString()
//            }
//            // 設定簡訊語系為繁體中文
//            auth.setLanguageCode("zh-Hant")
//            val phoneAuthOptions = PhoneAuthOptions.newBuilder(auth)
//                // 電話號碼，驗證碼寄送的電話號碼
//                .setPhoneNumber(mobile)
//                // 驗證碼失效時間，設為60秒代表即使多次請求驗證碼，過了60秒才會發送第2次
//                .setTimeout(60L, TimeUnit.SECONDS)
//                .setActivity(requireActivity())
//                // 監控電話驗證的狀態
//                .setCallbacks(verifyCallbacks)
//                .build()
//            PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions)
//        }







//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        with(binding) {
//
//            //送出驗證簡訊按鈕
//            btnSendPhoneMsg.setOnClickListener {  }
//
//
//
//
//
//
//
//
//            //驗證按鈕
//            btnSendPhoneMsg.setOnClickListener {  }
//
//
//
//            //按下一步,檢查值,挑轉至重設密碼
//            btnNext.setOnClickListener {
//                viewModel?.run {
//
//                    //手機
//                    if (login.value!!.phone.isEmpty()) {
//                        edtTxtForgetPhone.error = "手機不可以為空白"
//                        return@run
//                    } else {
//                        login.value!!.phone.matches(Regex("^\\d{10}$"))
//                    }
//
//                    //驗證碼
//                    if (login.value!!.code.isEmpty()) {
//                        edtTxtForgetConfirmNumber.error = "請先發送簡訊取得驗證碼"
//                        return@run
//                    }
//
//                    Navigation.findNavController(it).navigate(R.id.edTxtResetPassword)
//                }
//
//            }
//
//
//        }
//
//
//    }


}}