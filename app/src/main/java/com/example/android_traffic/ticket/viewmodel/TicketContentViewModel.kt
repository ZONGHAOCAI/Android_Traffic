package com.example.android_traffic.ticket.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.ticket.model.Content

class TicketContentViewModel : ViewModel() {
    val content : MutableLiveData<Content> by lazy { MutableLiveData<Content>() }

//    fun btnappeal () {
//        var a = TicketHistoryListViewModel()
//        a.loadTicketList()
//        when (a.loadTicketList()[1].status){
//            "已繳納" -> "1"
//        }
//    }
}