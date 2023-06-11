package com.example.android_traffic.chat.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_traffic.R
import com.example.android_traffic.chat.model.ChatPartner
import com.example.android_traffic.core.model.ChatRoom
import com.example.android_traffic.core.model.Ticket
import com.example.android_traffic.core.service.Server
import com.example.android_traffic.core.service.requestTask
import com.example.android_traffic.ticket.model.Content
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ChatRoomViewModel : ViewModel() {
    val myTag = "TAG_${javaClass.simpleName}"

    private var chatroomlist = mutableListOf<ChatRoom>()
    val chatroom: MutableLiveData<ChatRoom> by lazy { MutableLiveData<ChatRoom>() }
    val list: MutableLiveData<List<ChatRoom>> by lazy { MutableLiveData<List<ChatRoom>>() }
    val member: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun init() {
        val type = object : TypeToken<List<ChatRoom>>() {}.type
        list.value =
            requestTask<List<ChatRoom>>("${Server.urlChatRoom}/${member.value!!}", respBodyType = type)
        chatroomlist = list.value!!.toMutableList()
//        Log.d(myTag, "url: ${member.value!!}")
//        Log.d(myTag, "url: $url/${member.value!!}")
    }

    fun getNewChatRoom() {
        viewModelScope.launch {
            while (isActive) {
                val type = object : TypeToken<List<ChatRoom>>() {}.type
                val chatroom =
                    requestTask<List<ChatRoom>>("${Server.urlChatRoom}/${member.value!!}", respBodyType = type)
                val oldchatroom = mutableListOf<ChatRoom>()
                if (chatroom != null) {
                    for (i in chatroom) {
                        oldchatroom.add(i)
                    }
                }
//                Log.d("TAG_${javaClass.simpleName}", "oldList: ${oldchatroom} ")
                list.value = oldchatroom
//                Log.d("TAG_${javaClass.simpleName}", "list: ${list.value} ")
                delay(30000)
            }
        }
    }

    fun search(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            list.value = chatroomlist
        } else {
            val searchChatList = mutableListOf<ChatRoom>()
            chatroomlist.forEach { chatpartner ->
                if (chatpartner.nickname?.contains(newText, true) == true) {
                    searchChatList.add(chatpartner)
                }
            }
            list.value = searchChatList
        }
    }
}