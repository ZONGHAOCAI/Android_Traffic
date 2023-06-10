package com.example.android_traffic.login.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.core.model.Member
import com.example.android_traffic.core.service.Server
import com.example.android_traffic.core.service.requestTask
import com.example.android_traffic.login.model.Login

class LoginViewModel : ViewModel() {
   val login: MutableLiveData<Login> by lazy { MutableLiveData<Login>(Login()) }
   val loginResult: MutableLiveData<String> by lazy {MutableLiveData<String>()}
   val member: MutableLiveData<Member> by lazy { MutableLiveData(Member()) }

   fun init() {
//      member.value?.id = requestTask(Server.url, "OPTIONS")
//      member.value?.phoneNo = "922333444"
//      member.value?.password = "abc321"
   }

   fun login() :Boolean {
      val respBody = requestTask<Member>("${Server.url}/${member.value!!.phoneNo}/${member.value!!.password}")
      return respBody?.id != null
   }
}