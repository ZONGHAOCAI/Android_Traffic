package com.example.android_traffic.chat.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.chat.model.ChatContent

class ChatViewModel : ViewModel() {
    val avatar : MutableLiveData<ChatContent> by lazy { MutableLiveData<ChatContent>() }
}