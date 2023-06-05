package com.example.android_traffic.membercenter.viewmodel

import android.app.AlertDialog
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.example.android_traffic.R
import com.example.android_traffic.core.model.Member
import com.example.android_traffic.core.service.Server
import com.example.android_traffic.core.service.Server.Companion.url
import com.example.android_traffic.core.service.Server.Companion.urlFindID
import com.example.android_traffic.core.service.requestTask
import com.google.gson.JsonObject
import kotlinx.coroutines.launch


class MemberCenterViewModel : ViewModel() {
    val chatPermissions: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>(true) }

    init {
        chatPermissions?.value = requestTask<Member>(urlFindID)?.chatPermissions
    }
    /** 私訊權限 */
    fun chatPerminssionsS() {
        viewModelScope?.launch {
            var member: Member = Member()
            member.chatPermissions = chatPermissions?.value
            val respBody = requestTask<JsonObject>(Server.url, "PUT", member)
            respBody?.run {
                if (get("successful").asBoolean) {

                }
            }
        }
    }

    /** 登出 消除server的session     */
    fun logout(view: View){
        val alertDialog = AlertDialog.Builder(view.context)
            .setMessage("確認登出?")
            .setPositiveButton("是"){ _, _ ->
                requestTask<JsonObject>(url, "DELETE")
                Navigation.findNavController(view).popBackStack()
                Navigation.findNavController(view).navigate(R.id.loginFragment)
            }
            .setNegativeButton("否", null)
            .setCancelable(true)
            .show()
        //設定確定登出選項  3秒後才可以按
        val positiveButtion = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
        positiveButtion.isEnabled = false
        Handler(Looper.getMainLooper()).postDelayed({
            positiveButtion.isEnabled = true
        }, 3000)

    }
}
