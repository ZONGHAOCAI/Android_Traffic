package com.example.android_traffic.chat.viewmodel

import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_traffic.R
import com.example.android_traffic.chat.Util.ChatMessage
import com.example.android_traffic.chat.model.ChatContent
import com.example.android_traffic.chat.model.ChatPartner
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
//
///** 方便layout檔案中ImageView顯示載入圖片而建立的方法
// * build.gradle(Module)的plugins要加上「id 'kotlin-kapt'」設定 */
//@BindingAdapter("imagePath")
//fun downloadImage(imageView: ImageView, imagePath: String?) {
//
////    if (imagePath == null) { // 提供預設圖片
////        imageView.setImageResource(R.drawable.avatar1)
////    } else {
//        val oneMegabyte: Long = 1024 * 1024
//        val imageRef = imagePath?.let { FirebaseStorage.getInstance().reference.child(it) }
//        imageRef?.getBytes(oneMegabyte)?.addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                task.result?.let { bytes ->
//                    val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
//                    imageView.setImageBitmap(bitmap)
//                }
//            }
//        }
////    }
//}
//
///**
// * 單一景點資料處理
// */
//class ChatViewModel : ViewModel() {
//    val message: MutableLiveData<ChatMessage> by lazy { MutableLiveData<ChatMessage>() }
//
//    /** 取得新增document的ID後設定成景點ID */
//    fun setSpotId() {
//        // 因為單例模式，所以getInstance只會回傳同一viewmodel                                 document()執行後建立空的文件，尚未有內容ˊ
//        message.value?.id = FirebaseFirestore.getInstance().collection("message").document().id
//    }
//
//    /** 新增或修改Firestore的景點 */
//    fun addOrReplace(chatmessage: ChatMessage) {
//        viewModelScope.launch {
//            // 如果Firestore沒有該ID的Document就建立新的，已經有就更新內容
//            FirebaseFirestore.getInstance().collection("message").document(chatmessage.id).set(chatmessage).await()
//        }
//    }

//    /** 刪除Firestore的景點與圖片 */
//    fun delete(spot: Spot) {
//        viewModelScope.launch {
//            FirebaseFirestore.getInstance().collection("spots").document(spot.id).delete().await()
////                                如果不是空值則執行{}中的功能
//            spot.imagePath?.let { imagePath ->
//                FirebaseStorage.getInstance().reference.child(imagePath).delete().await()
//            }
//        }
//    }
//}

class ChatViewModel : ViewModel() {
    val avatar : MutableLiveData<ChatPartner> by lazy { MutableLiveData<ChatPartner>() }

    private var chatcontentlist = mutableListOf<ChatContent>()
    val chatcontent: MutableLiveData<List<ChatContent>> by lazy { MutableLiveData<List<ChatContent>>() }

    val chatext: MutableLiveData<ChatContent> by lazy { MutableLiveData<ChatContent>() }

    init {
        loadChatContentList()
    }

    private fun loadChatContentList() {
        var chatcontentlist = mutableListOf<ChatContent>()
        chatcontentlist.add(ChatContent(1, "我跟你說", "05/12", "03:12", null))
        chatcontentlist.add(ChatContent(1, "今天天氣那麼好", "05/12", "03:13", null))
        chatcontentlist.add(ChatContent(1, "要不要出去玩", "05/12", "03:14", null))
        chatcontentlist.add(ChatContent(2, null, "05/12", "03:20", R.drawable.avatar3))
        chatcontentlist.add(ChatContent(2, "好啊", "05/12", "03:23", null))
        chatcontentlist.add(ChatContent(1, "幾點集合？", "05/12", "03:42", null))
        chatcontentlist.add(ChatContent(1, null, "05/12", "03:43", R.drawable.avatar1))
        chatcontentlist.add(ChatContent(2, "11111111111111111111111111111111111222222222222222222222", "05/12", "03:44", null))
        chatcontentlist.add(ChatContent(2, null, "05/12", "03:50", R.drawable.avatar3))
        this.chatcontentlist = chatcontentlist
        this.chatcontent.value = this.chatcontentlist
    }

//    fun AddText() {
//        if (chatcontentlist.) {
//
//        }
//    }
}