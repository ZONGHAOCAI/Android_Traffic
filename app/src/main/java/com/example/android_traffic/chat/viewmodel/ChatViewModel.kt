package com.example.android_traffic.chat.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_traffic.R
import com.example.android_traffic.chat.model.ChatContent
import com.example.android_traffic.chat.model.ChatPartner
import com.example.android_traffic.core.model.Chat
import com.example.android_traffic.core.model.ChatRoom
import com.example.android_traffic.core.service.Server
import com.example.android_traffic.core.service.requestTask
import com.example.android_traffic.databinding.ChatContentBinding
import com.example.android_traffic.databinding.FragmentChatBinding
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    val chatroom: MutableLiveData<ChatRoom> by lazy { MutableLiveData<ChatRoom>() }
    val chat: MutableLiveData<Chat> by lazy { MutableLiveData<Chat>() }
    val myTag = "TAG_${javaClass.simpleName}"

    val list: MutableLiveData<List<Chat>> by lazy { MutableLiveData<List<Chat>>() }
    val member: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun init() {
        val type = object : TypeToken<List<Chat>>() {}.type
        list.value =
            requestTask<List<Chat>>(
                "${Server.urlChatRoom}/${chatroom.value?.ID!!}/${member.value!!}",
                respBodyType = type
            )
//        Log.d(myTag, "url: ${chatroom.value?.ID!!}")
//        Log.d(myTag, "url: $url/${member.value!!}")
    }

    fun getNewChat() {
        viewModelScope.launch {
            while (isActive) {
                val type = object : TypeToken<List<Chat>>() {}.type
                val chat = requestTask<List<Chat>>(
                    "${Server.urlChat}/${chatroom.value?.ID!!}/${member.value!!}",
                    respBodyType = type
                )
                val oldchat = mutableListOf<Chat>()
                if (chat != null) {
                    for (i in chat) {
                        oldchat.add(i)
                    }
                }
//                Log.d("TAG_${javaClass.simpleName}", "oldList: ${oldchat} ")
                list.value = oldchat
//                Log.d("TAG_${javaClass.simpleName}", "list: ${list.value} ")
                delay(10000)
            }
        }
    }
}