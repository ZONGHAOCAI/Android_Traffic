package com.example.android_traffic.chat.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_traffic.R
import com.example.android_traffic.chat.model.ChatContent
import com.example.android_traffic.chat.model.ChatPartner
import com.example.android_traffic.core.model.Chat
import com.example.android_traffic.core.model.ChatRoom
import com.example.android_traffic.core.service.requestTask
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    val chatroom: MutableLiveData<ChatRoom> by lazy { MutableLiveData<ChatRoom>() }
    val chat: MutableLiveData<Chat> by lazy { MutableLiveData<Chat>() }

    private val url = "http://10.0.2.2:8080/javaweb-Traffic/Chat/ChatController"

//    private var chatlist = mutableListOf<Chat>()
    val list: MutableLiveData<List<Chat>> by lazy { MutableLiveData<List<Chat>>() }
    val member: MutableLiveData<String> by lazy { MutableLiveData<String>() }
//    val chatext: MutableLiveData<ChatContent> by lazy { MutableLiveData<ChatContent>() }

    fun init() {
        val type = object : TypeToken<List<Chat>>() {}.type
        list.value =
            requestTask<List<Chat>>(
                "$url/${chatroom.value?.ID!!}/${member.value!!}",
                respBodyType = type
            )
        val myTag = "TAG_${javaClass.simpleName}"
        Log.d(myTag, "url: ${chatroom.value?.ID!!}")
        Log.d(myTag, "url: $url/${member.value!!}")
    }

    fun getNewChat() {
        viewModelScope.launch {
            while (isActive) {
                val type = object : TypeToken<List<Chat>>() {}.type
                val chat = requestTask<List<Chat>>(
                    "$url/${chatroom.value?.ID!!}/${member.value!!}",
                    respBodyType = type
                )
                val oldchat = mutableListOf<Chat>()
                if (chat != null) {
                    for (i in chat) {
                        oldchat.add(i)
                    }
                }
                Log.d("TAG_${javaClass.simpleName}", "oldList: ${oldchat} ")
                list.value = oldchat
                Log.d("TAG_${javaClass.simpleName}", "list: ${list.value} ")
                delay(30000)
            }
        }
    }
//    private fun loadChatContentList() {
//        var chatcontentlist = mutableListOf<ChatContent>()
//        chatcontentlist.add(ChatContent(1, "我跟你說", "05/12", "03:12", null))
//        chatcontentlist.add(ChatContent(1, "今天天氣那麼好", "05/12", "03:13", null))
//        chatcontentlist.add(ChatContent(1, "要不要出去玩", "05/12", "03:14", null))
//        chatcontentlist.add(ChatContent(2, null, "05/12", "03:20", R.drawable.avatar3))
//        chatcontentlist.add(ChatContent(2, "好啊", "05/12", "03:23", null))
//        chatcontentlist.add(ChatContent(1, "幾點集合？", "05/12", "03:42", null))
//        chatcontentlist.add(ChatContent(1, null, "05/12", "03:43", R.drawable.avatar1))
//        chatcontentlist.add(ChatContent(2, "11111111111111111111111111111111111222222222222222222222", "05/12", "03:44", null))
//        chatcontentlist.add(ChatContent(2, null, "05/12", "03:50", R.drawable.avatar3))
//        this.chatcontentlist = chatcontentlist
//        this.chatcontent.value = this.chatcontentlist
//    }


}