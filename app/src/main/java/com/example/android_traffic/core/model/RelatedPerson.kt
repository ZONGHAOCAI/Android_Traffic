package com.example.android_traffic.core.model

import java.io.Serializable
import java.util.Date

/**
 *
 * @param imageId 頭像
 * @param name 名子
 * @param identityNumber 身分證
 * @param birthday 生日日
 * @param MembersRelationship 跟會員的關係
 */
class RelatedPerson(var imageId: Int, var name: String, var identityNumber: String, var birthday: String,
                    var MembersRelationship: String) :
    Serializable {
}