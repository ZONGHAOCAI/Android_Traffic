package com.example.android_traffic.ticket.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.ticket.model.Content

class TicketAppealListViewModel : ViewModel() {
    private var appeallist = mutableListOf<Content>()
    val content: MutableLiveData<List<Content>> by lazy { MutableLiveData<List<Content>>() }

    init {
        loadTicketList()
    }

    fun search(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            content.value = appeallist
        } else {
            val searchTicketList = mutableListOf<Content>()
            appeallist.forEach { ticket ->
                if (ticket.number.contains(newText, true)) {
                    searchTicketList.add(ticket)
                }
            }
            content.value = searchTicketList
        }
    }

    private fun loadTicketList() {
        var appeallist = mutableListOf<Content>()
        appeallist.add(Content("C123","申訴中"))
        appeallist.add(Content("C143","申訴中"))
        appeallist.add(Content("C133","申訴中"))
        appeallist.add(Content("C163","申訴中"))
        appeallist.add(Content("C173","申訴中"))
        appeallist.add(Content("C183","申訴中"))
        appeallist.add(Content("C193","申訴中"))
        appeallist.add(Content("C103","申訴中"))
        appeallist.add(Content("C263","申訴中"))
        appeallist.add(Content("C273","申訴中"))
        appeallist.add(Content("C283","申訴中"))
        appeallist.add(Content("C293","申訴中"))
        appeallist.add(Content("C303","申訴中"))
        this.appeallist = appeallist
        this.content.value = this.appeallist
    }
}