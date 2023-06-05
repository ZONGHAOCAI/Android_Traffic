package com.example.android_traffic.chat.Util

import java.io.Serializable
import java.util.*

class ChatMessage : Serializable {
    lateinit var id: String
    var sendertoken : String? = null
    var receivertoken : String? = null
    var receivername: String? = null
    var receiveravatar: String? = null
    var messageText: String? = null
    var imagePath: String? = null
    var messageTime = Date()

    override fun equals(other: Any?): Boolean {
        return this.id == (other!! as ChatMessage).id
    }

    override fun hashCode(): Int {
        return id.hashCode() ?: 0
    }
}