package com.example.android_traffic.ticket.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.ticket.model.Content

class TicketHistoryListViewModel : ViewModel() {
    private var historylist = mutableListOf<Content>()
    val content: MutableLiveData<List<Content>> by lazy { MutableLiveData<List<Content>>() }

    init {
        loadTicketList()
    }

    fun search(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            content.value = historylist
        } else {
            val searchTicketList = mutableListOf<Content>()
            historylist.forEach { ticket ->
                if (ticket.number.contains(newText, true)) {
                    searchTicketList.add(ticket)
                }
            }
            content.value = searchTicketList
        }
    }

    fun loadTicketList(): MutableList<Content> {
        var historylist = mutableListOf<Content>()
        historylist.add(Content("H123","已繳納"))
        historylist.add(Content("H143","申訴成功"))
        historylist.add(Content("H133","已繳納"))
        historylist.add(Content("H163","申訴成功"))
        historylist.add(Content("H173","已繳納"))
        historylist.add(Content("H183","已繳納"))
        historylist.add(Content("H193","已繳納"))
        historylist.add(Content("H103","已繳納"))
        historylist.add(Content("H263","已繳納"))
        historylist.add(Content("H273","已繳納"))
        historylist.add(Content("H283","已繳納"))
        historylist.add(Content("H293","已繳納"))
        historylist.add(Content("H303","已繳納"))
        this.historylist = historylist
        this.content.value = this.historylist
        return historylist
    }
}