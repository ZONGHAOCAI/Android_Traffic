package com.example.android_traffic.core.model

import java.io.Serializable

data class ChatRoom(
    var ID: Int? = null, // ID
    var memID1: Int? = null, // 會員ID_1
    var memID2: Int? = null, // 會員ID_2
    var nickname: String? = null, // 另外一個使用者的暱稱
    var avatar: ByteArray? = null, // 另外一個使用者的頭貼
    var avatarBase64: String? = null,
    var unread: Int? = null // 未讀數量
) : Serializable