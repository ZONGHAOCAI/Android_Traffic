package com.example.android_traffic.ticket.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_traffic.MainViewModel
import com.example.android_traffic.R
import com.example.android_traffic.core.model.Member
import com.example.android_traffic.core.model.Ticket
import com.example.android_traffic.core.service.requestTask
import com.example.android_traffic.ticket.model.Content
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class TicketUnpaidListViewModel : ViewModel() {
    private var unpaidlist = mutableListOf<Ticket>()

    private val url = "http://10.0.2.2:8080/javaweb-Traffic/Ticket/FindTicketByMemId"

    val ticket: MutableLiveData<Ticket> by lazy { MutableLiveData<Ticket>() }
    val list: MutableLiveData<List<Ticket>> by lazy { MutableLiveData<List<Ticket>>() }
    val member: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun init() {
        val type = object : TypeToken<List<Ticket>>() {}.type
        list.value =
            requestTask<List<Ticket>>("$url/${member.value!!}/0", respBodyType = type)
//        val myTag = "TAG_${javaClass.simpleName}"
//        Log.d(myTag, "id: ${member.value!!}")
//        Log.d(myTag, "url: $url/${member.value!!}/0")
    }

    fun getNewTicket() {
        viewModelScope.launch {
            while (isActive) {
                val type = object : TypeToken<List<Ticket>>() {}.type
                val ticket = requestTask<List<Ticket>>("$url/${member.value!!}/0", respBodyType = type)
                val oldTicket = mutableListOf<Ticket>()
                if (ticket != null) {
                    for (i in ticket) {
                        oldTicket.add(i)
                    }
                }
                Log.d("TAG_${javaClass.simpleName}", "oldMessageList: ${oldTicket} ")
                list.value = oldTicket
                Log.d("TAG_${javaClass.simpleName}", "list: ${list.value} ")
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