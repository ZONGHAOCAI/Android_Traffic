package com.example.android_traffic

import java.io.Serializable
import java.sql.Timestamp

data class Member(
    var id: Int? = null,
    var name: String = "",
    var password: String = "",
    var nickname: String = "",
    var identityNumber: String = "",
    var birthday: String = "",
    var phoneNo: String = "",
    var address: String = "",
    var email: String = "",
    var avatar: String? = null,
    var forumPermissions: Boolean? = null,
    var chatPermissions: Boolean? = null,
    var Timestamp: Timestamp? = null
) : Serializable