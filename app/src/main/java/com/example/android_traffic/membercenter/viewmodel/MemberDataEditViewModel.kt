package com.example.android_traffic.membercenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.core.model.Member
import com.example.android_traffic.core.service.Server.Companion.url
import com.example.android_traffic.core.service.requestTask

class MemberDataEditViewModel : ViewModel() {
    val memberData: MutableLiveData<String> by lazy { MutableLiveData("") }
    val memberImg: MutableLiveData<Member> by lazy { MutableLiveData(Member()) }
    var title: MutableLiveData<String> = MutableLiveData()
    val pass: MutableLiveData<String> by lazy { MutableLiveData() }
    val cPass: MutableLiveData<String> by lazy { MutableLiveData() }

//    val memberData: MutableLiveData<String> by lazy { MutableLiveData() }

    var memberID = 0

    fun init() {
        val memberId: Int? = requestTask<Int>(url, "OPTIONS") //取得登入的session記的memberID
        if (memberId != null) {
            this.memberID = memberId
            println("memberDataEdit拿到ID了${memberID}")
        }
    }


}