package com.example.android_traffic.chat.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.R
import com.example.android_traffic.chat.model.ChatContent
import com.example.android_traffic.chat.model.ChatPartner

class ChatViewModel : ViewModel() {
    val avatar : MutableLiveData<ChatPartner> by lazy { MutableLiveData<ChatPartner>() }

    private var chatcontentlist = mutableListOf<ChatContent>()
    val chatcontent: MutableLiveData<List<ChatContent>> by lazy { MutableLiveData<List<ChatContent>>() }

    val chatext: MutableLiveData<ChatContent> by lazy { MutableLiveData<ChatContent>() }

    init {
        loadChatContentList()
    }

    private fun loadChatContentList() {
        var chatcontentlist = mutableListOf<ChatContent>()
        chatcontentlist.add(ChatContent(1, "我跟你說", "05/12", "03:12", null))
        chatcontentlist.add(ChatContent(1, "今天天氣那麼好", "05/12", "03:13", null))
        chatcontentlist.add(ChatContent(1, "要不要出去玩", "05/12", "03:14", null))
        chatcontentlist.add(ChatContent(2, null, "05/12", "03:20", R.drawable.avatar3))
        chatcontentlist.add(ChatContent(2, "好啊", "05/12", "03:23", null))
        chatcontentlist.add(ChatContent(1, "幾點集合？", "05/12", "03:42", null))
        chatcontentlist.add(ChatContent(1, null, "05/12", "03:43", R.drawable.avatar1))
        chatcontentlist.add(ChatContent(2, "11111111111111111111111111111111111222222222222222222222", "05/12", "03:44", null))
        this.chatcontentlist = chatcontentlist
        this.chatcontent.value = this.chatcontentlist
    }
}