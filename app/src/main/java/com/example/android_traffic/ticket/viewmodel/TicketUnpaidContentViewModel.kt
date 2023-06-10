package com.example.android_traffic.ticket.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.core.model.Ticket
import com.example.android_traffic.ticket.model.Content

class TicketUnpaidContentViewModel : ViewModel() {
    val content : MutableLiveData<Ticket> by lazy { MutableLiveData<Ticket>() }
    
}