package com.example.android_traffic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.login.model.Login

class ResetPasswordViewModel : ViewModel() {
    val login: MutableLiveData<Login> by lazy { MutableLiveData<Login>(Login()) }
}