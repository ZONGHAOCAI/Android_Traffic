package com.example.android_traffic.core.model

import java.io.Serializable
import java.sql.Timestamp

data class Chat(
    var ID: Int? = null, // ID
    var senderID: Int? = null, // 傳送者ID
    var chatroomID: Int? = null, // 聊天室UID
    var content: String? = null, // 內容
    var appendix: ByteArray? = null, // 附件
    var appendixBase64: String? = null,
    var sendTime: Timestamp? = null, // 傳送時間
    var readStatus: Boolean = false, // 已讀狀態
    var memId1: Int? = null,
    var memId2: Int? = null,
    var nickname: String? = null, // 另外一個使用者的暱稱
    var avatar: ByteArray? = null, // 另外一個使用者的頭貼
    var avatarBase64: String? = null,
) : Serializable