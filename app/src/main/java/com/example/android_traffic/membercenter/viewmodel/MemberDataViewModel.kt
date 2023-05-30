package com.example.android_traffic.membercenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.Server.Companion.url
import com.example.android_traffic.Server.Companion.urlFindID
import com.example.android_traffic.Member
import com.example.android_traffic.getMemberData
import com.example.android_traffic.getSession
import com.example.android_traffic.service.requestTask

class MemberDataViewModel : ViewModel() {


    val nowPassword: String = ""
    val member: MutableLiveData<Member> by lazy { MutableLiveData(Member()) }

    fun init() {
        val id: Int? = getSession()
        member.value = getMemberData()
    }
}