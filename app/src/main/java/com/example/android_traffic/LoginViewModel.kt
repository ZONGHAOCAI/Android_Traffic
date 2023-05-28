package com.example.android_traffic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.login.Login

class LoginViewModel : ViewModel() {
   val login: MutableLiveData<Login> by lazy { MutableLiveData<Login>() }
   val loginResult: MutableLiveData<String> by lazy {MutableLiveData<String>()}
}