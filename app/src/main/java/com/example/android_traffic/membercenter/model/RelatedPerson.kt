package com.example.android_traffic.membercenter.model

import java.io.Serializable
import java.util.Date

/**
 *
 * @param imageId 頭像
 * @param name 名子
 * @param identityNumber 身分證
 * @param birthday 生日日
 * @param MembersRelationship 跟會員的關係
 * @param VehideType 車種
 * @param VehideNumber 車牌號碼
 */
class RelatedPerson(var imageId: Int, var name: String, var identityNumber: String, var birthday: String,
                    var MembersRelationship: String) :
    Serializable {
}