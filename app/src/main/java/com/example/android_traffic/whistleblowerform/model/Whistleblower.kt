package com.example.android_traffic.whistleblowerform.model

import java.io.Serializable
import java.sql.Date
import java.time.LocalDate


data class Whistleblower(
    var memId: String? = null,
    var violationsTime: String? = null,  //時間
    var vehicleType: String? = null,    //車種
    var violationsAddress: String? = null,  //違規地點
    var violations: String? = null,  //違規種類
//    var violationIntersection: String? = null,  //交叉入口??
    var remark: String? = null,  //地點備註
    var violationsDate: String? = null,  //違規事實??  改違規日期
    var vehicleNo: String? = null,  //違規說明??  改車牌
): Serializable
