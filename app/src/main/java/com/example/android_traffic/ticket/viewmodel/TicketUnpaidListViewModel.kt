package com.example.android_traffic.ticket.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.MainViewModel
import com.example.android_traffic.R
import com.example.android_traffic.core.model.Member
import com.example.android_traffic.core.model.Ticket
import com.example.android_traffic.core.service.requestTask
import com.example.android_traffic.ticket.model.Content
import com.google.gson.reflect.TypeToken

class TicketUnpaidListViewModel : ViewModel() {
    //    private var unpaidlist = mutableListOf<Content>()
    private var unpaidlist = mutableListOf<Ticket>()

    private val url = "http://10.0.2.2:8080/javaweb-Traffic/Ticket/FindTicketByMemId"

    //    val content: MutableLiveData<List<Content>> by lazy { MutableLiveData<List<Content>>() }
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
//    init {
//        loadUnpaidList()
//    }

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

//    private fun loadUnpaidList() {
//        var unpaidlist = mutableListOf<Content>()
//        unpaidlist.add(Content("A123","1天", listOf(R.drawable.avatar1, R.drawable.avatar2, R.drawable.avatar3, R.drawable.avatar2, R.drawable.avatar1)))
//        unpaidlist.add(Content("A143","1天", listOf(R.drawable.avatar1, R.drawable.avatar2)))
//        unpaidlist.add(Content("A133","1天", listOf(R.drawable.avatar2)))
//        unpaidlist.add(Content("A163","2天", listOf(R.drawable.avatar3)))
//        unpaidlist.add(Content("A173","3天", listOf(R.drawable.avatar2)))
//        unpaidlist.add(Content("A183","3天", listOf(R.drawable.avatar1)))
//        unpaidlist.add(Content("A193","4天", listOf(R.drawable.avatar2)))
//        unpaidlist.add(Content("A124","1天", listOf(R.drawable.avatar3)))
//        unpaidlist.add(Content("A125","1天", listOf(R.drawable.avatar2)))
//        unpaidlist.add(Content("A103","4天", listOf(R.drawable.avatar1)))
//        unpaidlist.add(Content("A263","4天", listOf(R.drawable.avatar2)))
//        unpaidlist.add(Content("A273","4天", listOf(R.drawable.avatar3)))
//        unpaidlist.add(Content("A283","4天", listOf(R.drawable.avatar2)))
//        unpaidlist.add(Content("A293","4天", listOf(R.drawable.avatar1)))
//        unpaidlist.add(Content("A303","4天", listOf(R.drawable.avatar2)))
//        this.unpaidlist = unpaidlist
//        this.content.value = this.unpaidlist
//    }
}