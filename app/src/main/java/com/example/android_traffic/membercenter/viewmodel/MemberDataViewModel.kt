package com.example.android_traffic.membercenter.viewmodel

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.android_traffic.R
import com.example.android_traffic.core.model.Member

import com.example.android_traffic.core.service.Server.Companion.url
import com.example.android_traffic.core.service.Server.Companion.urlFindID
import com.example.android_traffic.core.service.requestTask

class MemberDataViewModel : ViewModel() {
    val member: MutableLiveData<Member> by lazy { MutableLiveData(Member()) }
    val bundle = Bundle()

    fun init() {
        member?.value = requestTask<Member>(urlFindID)
    }

    /**
     * 將要修改的功能跟資料傳到修改資料頁
     * @param type 修改頁面要做的事情 EX:"name" 修改姓名
     */
    private fun sendBundle(type: String, view: View) {
        bundle.putSerializable("type", type)
        bundle.putSerializable("memberData", member?.value)
        Navigation.findNavController(view).navigate(
                R.id.action_memberDataFragment_to_memberDataEditFragment, bundle)
    }

    fun editName(view: View) {
        sendBundle("Name", view)
    }
    fun editNickname(view: View) {
        sendBundle("NickName", view)
    }
    fun editIdentityNumber(view: View) {
        sendBundle("IdentityNumber", view)
    }
    fun editBirthday(view: View) {
        sendBundle("Birthday", view)
    }
    fun editEmail(view: View) {
        sendBundle("Email", view)
    }
    fun editAddress(view: View) {
        sendBundle("Address", view)
    }
    fun editPassword(view: View) {
        sendBundle("Password", view)
    }


}
