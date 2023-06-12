package com.example.android_traffic.ticket.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_traffic.core.model.Ticket
import com.example.android_traffic.core.service.Server
import com.example.android_traffic.core.service.Server.Companion.urlHistoryTicket
import com.example.android_traffic.core.service.requestTask
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class TicketHistoryListViewModel : ViewModel() {
    private var historylist = mutableListOf<Ticket>()

    val list: MutableLiveData<List<Ticket>> by lazy { MutableLiveData<List<Ticket>>() }
    val member: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun init() {
        val type = object : TypeToken<List<Ticket>>() {}.type
        list.value =
            requestTask<List<Ticket>>("${urlHistoryTicket}/${member.value!!}", respBodyType = type)
        historylist = list.value!!.toMutableList()
    }

    fun getNewTicket() {
        viewModelScope.launch {
            while (isActive) {
                val type = object : TypeToken<List<Ticket>>() {}.type
                val ticket =
                    requestTask<List<Ticket>>(
                        "${Server.urlHistoryTicket}/${member.value!!}",
                        respBodyType = type
                    )
                val oldTicket = mutableListOf<Ticket>()
                if (ticket != null) {
                    for (i in ticket) {
                        oldTicket.add(i)
                    }
                }
                list.value = oldTicket
                delay(30000)
            }
        }
    }

    fun search(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            list.value = historylist
        } else {
            val searchTicketList = mutableListOf<Ticket>()
            historylist.forEach { ticket ->
                if (ticket.ticketNo?.contains(newText, true) == true) {
                    searchTicketList.add(ticket)
                }
            }
            list.value = searchTicketList
        }
    }
}