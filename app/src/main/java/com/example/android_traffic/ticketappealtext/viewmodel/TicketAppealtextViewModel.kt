package com.example.android_traffic.ticketappealtext.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.ticketappealtext.model.Appeal

class TicketAppealtextViewModel : ViewModel() {
    val appeal: MutableLiveData<Appeal> by lazy { MutableLiveData<Appeal>() }
    val menu: MutableLiveData<List<String>>
            by lazy {
                MutableLiveData<List<String>>(
                    listOf(
                        "在交岔路口十公尺內臨時停車", "轉彎或變換車道不依標誌、標線、號誌指示" +
                                "汽車駕駛人未依規定使用方向燈", "併排臨時停車", "汽車駕駛人行車速度，超過規定之最高時速或低於規定之最低時速"
                    )
                )
            }
}