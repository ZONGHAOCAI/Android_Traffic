package com.example.android_traffic.chat.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.R
import com.example.android_traffic.chat.model.ChatPartner

class ChatRoomViewModel : ViewModel() {
    private var chatlist = mutableListOf<ChatPartner>()
    val content: MutableLiveData<List<ChatPartner>> by lazy { MutableLiveData<List<ChatPartner>>() }

    init {
        loadChatList()
    }

    private fun loadChatList() {
        var chatlist = mutableListOf<ChatPartner>()
        chatlist.add(ChatPartner(R.drawable.avatar1,"暱稱 : 黑貓貓貓貓貓貓lllllllllllllll貓貓貓"))
        chatlist.add(ChatPartner(R.drawable.avatar2,"暱稱 : 虎斑貓"))
        chatlist.add(ChatPartner(R.drawable.avatar3,"暱稱 : 玳瑁貓"))
        chatlist.add(ChatPartner(R.drawable.avatar1,"暱稱 : 橘貓"))
        chatlist.add(ChatPartner(R.drawable.avatar2,"暱稱 : 乳牛貓"))
        chatlist.add(ChatPartner(R.drawable.avatar3,"暱稱 : 賓士貓"))
        chatlist.add(ChatPartner(R.drawable.avatar1,"暱稱 : 白貓"))
        chatlist.add(ChatPartner(R.drawable.avatar2,"暱稱 : 英短"))
        chatlist.add(ChatPartner(R.drawable.avatar3,"暱稱 : 布偶貓"))
        chatlist.add(ChatPartner(R.drawable.avatar1,"暱稱 : 波絲貓"))
        chatlist.add(ChatPartner(R.drawable.avatar2,"暱稱 : 三花貓"))
        chatlist.add(ChatPartner(R.drawable.avatar3,"暱稱 : 美短"))
        this.chatlist = chatlist
        this.content.value = this.chatlist
    }
}