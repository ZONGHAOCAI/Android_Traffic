package com.example.android_traffic.core.model

import java.io.Serializable
import java.sql.Timestamp

/**
 * 會員資料用
 * @param 會員ID
 * @param 姓名
 * @param 密碼
 * @param 暱稱
 * @param 身分證號碼
 * @param 生日
 * @param 手機
 * @param 地址
 * @param 信箱
 * @param 頭像
 * @param 論壇權限
 * @param 私訊權限
 * @param 建立時間//沒啥用
 */
data class ForumArticle(
    var id: Int? = null,
    var memberID: Int? = null,
    var content: String = "",
    var status: Boolean?= null,
    var editTime: Timestamp? = null,
    var createTime: Timestamp? = null,
    var title: String = ""
) : Serializable
