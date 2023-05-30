package com.example.android_traffic

import androidx.navigation.Navigation
import com.example.android_traffic.service.requestTask
import com.google.gson.JsonObject

class Server {
    companion object {
        const val url = "http://10.0.2.2:8080/javaweb-Traffic/Login"
        /*  login  輸入手機跟密碼 回傳member物件
            註冊 還沒弄

            取得登入記session的memberID
            val memberId: Int? = requestTask<Int>(url, "OPTIONS")
         */

        const val urlFindID = "http://10.0.2.2:8080/javaweb-Traffic/FindMemberByID"
        /*  查詢會員的資料 傳入ID 回傳會員資料 id 是int 論壇權限跟私訊功能是布林 其他都string
            member.value = requestTask<Member>("$urlFindID/$id")
        */

        //以下預定
        const val urlMemberVehideData = "http://10.0.2.2:8080/javaweb-Traffic/MemberVehide" //會員的車輛資料
        const val urlRelatedpersonVehideData = "http://10.0.2.2:8080/javaweb-Traffic/RelatedpersonVehide" //關係人的車輛資料
//        const val urlFindID = "http://10.0.2.2:8080/javaweb-Traffic/FindMemberByID"
//        const val urlFindID = "http://10.0.2.2:8080/javaweb-Traffic/FindMemberByID"
//        const val urlFindID = "http://10.0.2.2:8080/javaweb-Traffic/FindMemberByID"
//        const val urlFindID = "http://10.0.2.2:8080/javaweb-Traffic/FindMemberByID"
    }
}

/**
 * 編輯個人資料
 * 傳入member物件 回傳JsonObject?
 */
fun editMemberData(editData: Member): JsonObject? {
    return requestTask<JsonObject>(Server.url, "PUT", editData)
    //val respBody = editMemberData(editData)
    //respBody?.run {
    //    if (get("successful").asBoolean) { //如果編輯成功的話
    //        Navigation.findNavController(it).popBackStack()
}

/**
 * 登入用 傳入phoneNo 跟 password 回傳member物件(裡面只有ID)
 */
fun login(phoneNo: String, password: String): Member? {
    return requestTask<Member>("${Server.url}/$phoneNo/$password")
//    val respBody = login(member.value!!.phoneNo, member.value!!.password)
//    if (respBody?.id != null){
//        Navigation.findNavController(it).navigate(R.id.memberCenterFragment)
}

/**
 * 取得登入的session記的memberID
 */
fun getSession(): Int? {
    return requestTask<Int>(Server.url, "OPTIONS")
//    val id: Int? = getSession()
}

/**
 * 取得會員資料 直接呼叫 回傳member
 * id 是int 論壇權限跟私訊功能是布林 其他都string
 */
fun getMemberData(): Member? {
    return requestTask<Member>(Server.urlFindID)
    //member.value = getMemberData()
}