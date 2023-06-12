package com.example.android_traffic.ticket.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_traffic.R
import com.example.android_traffic.core.model.Ticket
import com.example.android_traffic.core.service.Server
import com.example.android_traffic.core.service.requestTask
import com.example.android_traffic.ticket.model.Content
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class TicketAppealListViewModel : ViewModel() {
    private var appeallist = mutableListOf<Ticket>()

    //    val ticket: MutableLiveData<Ticket> by lazy { MutableLiveData<Ticket>() }
    val list: MutableLiveData<List<Ticket>> by lazy { MutableLiveData<List<Ticket>>() }
    val member: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun init() {
        val type = object : TypeToken<List<Ticket>>() {}.type

        list.value =
            requestTask<List<Ticket>>(
                "${Server.urlTicket}/${member.value!!}/2",
                respBodyType = type
            )
        appeallist = list.value!!.toMutableList()
    }

    fun getNewTicket() {
        viewModelScope.launch {
            while (isActive) {
                val type = object : TypeToken<List<Ticket>>() {}.type
                val ticket =
                    requestTask<List<Ticket>>(
                        "${Server.urlHistoryTicket}/${member.value!!}/2",
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
            list.value = appeallist
        } else {
            val searchTicketList = mutableListOf<Ticket>()
            appeallist.forEach { ticket ->
                if (ticket.ticketNo?.contains(newText, true) == true) {
                    searchTicketList.add(ticket)
                }
            }
            list.value = searchTicketList
        }
    }
}