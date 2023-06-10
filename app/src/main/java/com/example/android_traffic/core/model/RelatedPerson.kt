package com.example.android_traffic.core.model

import java.io.Serializable

/**
 *
 * @param imageId 頭像
 * @param name 名子
 * @param identityNumber 身分證
 * @param birthday 生日日
 * @param memberRelationship 跟會員的關係
 */
data class RelatedPerson(
    var id: Int? = null,
//    var imageId: Int? = null,
    var name: String = "",
    var identityNumber: String = "",
    var birthday: String = "",
    var avatarBase64 : String? = null,
    var memberRelationship: String = "") :
    Serializable {
}