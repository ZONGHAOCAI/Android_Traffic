package com.example.android_traffic.membercenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.core.model.Member

import com.example.android_traffic.core.service.Server.Companion.url
import com.example.android_traffic.core.service.Server.Companion.urlFindID
import com.example.android_traffic.core.service.requestTask

class MemberDataViewModel : ViewModel() {
    val member: MutableLiveData<Member> by lazy { MutableLiveData(Member()) }
    fun init() {
        val id: Int? = requestTask<Int>(url, "OPTIONS")
        member?.value = requestTask<Member>(urlFindID)
    }
}