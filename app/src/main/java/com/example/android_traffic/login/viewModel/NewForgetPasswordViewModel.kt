package com.example.android_traffic.login.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewForgetPasswordViewModel : ViewModel() {
    val mobile: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val verificationCode: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    // 一開始先隱藏填寫驗證碼版面
    val layoutVisible: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>(false) }
    val text: MutableLiveData<String> by lazy { MutableLiveData<String>() }
}