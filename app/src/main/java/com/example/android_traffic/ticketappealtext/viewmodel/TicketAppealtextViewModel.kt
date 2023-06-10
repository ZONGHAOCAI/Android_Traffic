package com.example.android_traffic.ticketappealtext.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TicketAppealtextViewModel : ViewModel() {
    val con:MutableLiveData<String>by lazy { MutableLiveData<String>() }
    val contwo:MutableLiveData<String> by lazy { MutableLiveData<String>() }
}