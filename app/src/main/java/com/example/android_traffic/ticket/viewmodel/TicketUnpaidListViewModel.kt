package com.example.android_traffic.ticket.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_traffic.core.model.Ticket
import com.example.android_traffic.core.service.Server.Companion.urlTicket
import com.example.android_traffic.core.service.requestTask
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class TicketUnpaidListViewModel : ViewModel() {
    private var unpaidlist = mutableListOf<Ticket>()

    //    val ticket: MutableLiveData<Ticket> by lazy { MutableLiveData<Ticket>() }
    val list: MutableLiveData<List<Ticket>> by lazy { MutableLiveData<List<Ticket>>() }
    val member: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun init() {
        val type = object : TypeToken<List<Ticket>>() {}.type

        list.value =
            requestTask<List<Ticket>>("$urlTicket/${member.value!!}/0", respBodyType = type)
        unpaidlist = list.value!!.toMutableList()
//        val myTag = "TAG_${javaClass.simpleName}"
//        Log.d(myTag, "id: ${member.value!!}")
//        Log.d(myTag, "url: $urlTicket/${member.value!!}/0")
    }

    fun getNewTicket() {
        viewModelScope.launch {
            while (isActive) {
                val type = object : TypeToken<List<Ticket>>() {}.type
                val ticket =
                    requestTask<List<Ticket>>("$urlTicket/${member.value!!}/0", respBodyType = type)
                val oldTicket = mutableListOf<Ticket>()
                if (ticket != null) {
                    for (i in ticket) {
                        oldTicket.add(i)
                    }
                }
//                Log.d("TAG_${javaClass.simpleName}", "oldMessageList: ${oldTicket} ")
                list.value = oldTicket
//                Log.d("TAG_${javaClass.simpleName}", "list: ${list.value} ")
                delay(30000)
            }
        }
    }

    fun search(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            list.value = unpaidlist
        } else {
            val searchTicketList = mutableListOf<Ticket>()
            unpaidlist.forEach { ticket ->
                if (ticket.ticketNo?.contains(newText, true) == true) {
                    searchTicketList.add(ticket)
                }
            }
            list.value = searchTicketList
        }
    }
}