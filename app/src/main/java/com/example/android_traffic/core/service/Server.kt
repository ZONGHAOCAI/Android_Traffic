package com.example.android_traffic.core.service

class Server {
    companion object {
        const val url = "http://10.0.2.2:8080/javaweb-Traffic/Login"
        /*  login  輸入手機跟密碼 回傳member物件(裡面只有ID)
            val respBody = requestTask<Member>("$url/${member.value!!.phoneNo}/${member.value!!.password}")
            if (respBody?.id != null){
                 Navigation.findNavController(it).navigate(R.id.memberCenterFragment)

            註冊 還沒弄

            取得登入記在session的memberID
            val memberId: Int? = requestTask<Int>(url, "OPTIONS")

            編輯個人資料 (傳入member物件 回傳JsonObject?)
            val respBody = requestTask<JsonObject>(url, "PUT", editData)
            val respBody = editMemberData(editData)
            respBody?.run {
                if (get("successful").asBoolean) { //如果編輯成功的話
                    Navigation.findNavController(it).popBackStack()

            登出
         */

        const val urlFindID = "http://10.0.2.2:8080/javaweb-Traffic/FindMemberByID"
        /*  查詢會員的資料 呼叫會回傳會員資料 id 是int 論壇權限跟私訊功能是布林 其他都string

            member?.value = requestTask<Member>(urlFindID)
        */

        //以下預定
        const val urlVehide = "http://10.0.2.2:8080/javaweb-Traffic/Vehide" //會員的車輛資料
        const val urlRelatedpersonVehideData = "http://10.0.2.2:8080/javaweb-Traffic/RelatedpersonVehide" //關係人的車輛資料
        const val urlFindRelatedperson = "http://10.0.2.2:8080/javaweb-Traffic/FindRelatedperson" //關係人資料 新增跟查詢
        const val urlForumArticle = "http://10.0.2.2:8080/javaweb-Traffic/ForumArticle"
        const val urlForumComment = "http://10.0.2.2:8080/javaweb-Traffic/ForumComment"
        const val urlFineAppeal = "http://10.0.2.2:8080/javaweb-Traffic/FineAppeal"

        const val urlChatRoom = "http://10.0.2.2:8080/javaweb-Traffic/Chat/ChatRoomController"
        const val urlChat = "http://10.0.2.2:8080/javaweb-Traffic/Chat/ChatController"
    }
}


