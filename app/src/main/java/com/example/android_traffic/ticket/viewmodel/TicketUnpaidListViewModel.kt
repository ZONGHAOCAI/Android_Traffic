package com.example.android_traffic.ticket.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.R
import com.example.android_traffic.ticket.model.Content

class TicketUnpaidListViewModel : ViewModel() {
    private var unpaidlist = mutableListOf<Content>()
    val content: MutableLiveData<List<Content>> by lazy { MutableLiveData<List<Content>>() }

    init {
        loadUnpaidList()
    }

    fun search(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            content.value = unpaidlist
        } else {
            val searchTicketList = mutableListOf<Content>()
            unpaidlist.forEach { ticket ->
                if (ticket.number.contains(newText, true)) {
                    searchTicketList.add(ticket)
                }
            }
            content.value = searchTicketList
        }
    }

    private fun loadUnpaidList() {
        var unpaidlist = mutableListOf<Content>()
        unpaidlist.add(Content("A123","1天", listOf(R.drawable.avatar1, R.drawable.avatar2, R.drawable.avatar3, R.drawable.avatar2, R.drawable.avatar1)))
        unpaidlist.add(Content("A143","1天", listOf(R.drawable.avatar1, R.drawable.avatar2)))
        unpaidlist.add(Content("A133","1天", listOf(R.drawable.avatar2)))
        unpaidlist.add(Content("A163","2天", listOf(R.drawable.avatar3)))
        unpaidlist.add(Content("A173","3天", listOf(R.drawable.avatar2)))
        unpaidlist.add(Content("A183","3天", listOf(R.drawable.avatar1)))
        unpaidlist.add(Content("A193","4天", listOf(R.drawable.avatar2)))
        unpaidlist.add(Content("A124","1天", listOf(R.drawable.avatar3)))
        unpaidlist.add(Content("A125","1天", listOf(R.drawable.avatar2)))
        unpaidlist.add(Content("A103","4天", listOf(R.drawable.avatar1)))
        unpaidlist.add(Content("A263","4天", listOf(R.drawable.avatar2)))
        unpaidlist.add(Content("A273","4天", listOf(R.drawable.avatar3)))
        unpaidlist.add(Content("A283","4天", listOf(R.drawable.avatar2)))
        unpaidlist.add(Content("A293","4天", listOf(R.drawable.avatar1)))
        unpaidlist.add(Content("A303","4天", listOf(R.drawable.avatar2)))
        this.unpaidlist = unpaidlist
        this.content.value = this.unpaidlist
    }
}