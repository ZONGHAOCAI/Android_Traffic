package com.example.android_traffic.ticket.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.ticket.model.Content

class TicketContentViewModel : ViewModel() {
    val content : MutableLiveData<Content> by lazy { MutableLiveData<Content>() }
}