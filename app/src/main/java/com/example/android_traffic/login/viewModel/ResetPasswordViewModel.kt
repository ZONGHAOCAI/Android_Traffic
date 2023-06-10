package com.example.android_traffic.login.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.core.model.Member
import com.example.android_traffic.core.service.Server.Companion.url
import com.example.android_traffic.core.service.requestTask
import com.example.android_traffic.login.model.Login
import com.google.gson.JsonObject

class ResetPasswordViewModel : ViewModel() {
    val login: MutableLiveData<Login> by lazy { MutableLiveData<Login>(Login())}
    val member: MutableLiveData<Member> by lazy { MutableLiveData(Member()) }
    val confirmPassword: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    fun resetPassword(){
        requestTask<JsonObject>("http://10.0.2.2:8080/javaweb-Traffic/ForgetPassword", "PUT", member.value)
    }

    //測試

}