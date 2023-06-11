package com.example.android_traffic.chat.controller

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.android_traffic.R
import com.example.android_traffic.chat.viewmodel.ChatViewModel
import com.example.android_traffic.core.model.ChatRoom
import com.example.android_traffic.databinding.ChatNameBinding

class ChatRoomAdapter(private var content: List<ChatRoom>) :
    RecyclerView.Adapter<ChatRoomAdapter.Holder>() {
    val myTag = "TAG_${javaClass.simpleName}"

    fun updateChatList(chatroomcontent: List<ChatRoom>) {
        this.content = chatroomcontent
        notifyDataSetChanged()
    }

    class Holder(val chatnameBinding: ChatNameBinding) :
        RecyclerView.ViewHolder(chatnameBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val chatnameBinding =
            ChatNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        chatnameBinding.viewmodel = ChatViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
//        Log.d(myTag, "parent: ${parent}")
//        Log.d(myTag, "findViewTreeLifecycleOwner: ${parent.findViewTreeLifecycleOwner()}")

        chatnameBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return Holder(chatnameBinding)
    }

    override fun getItemCount(): Int {
        return content.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        with(holder) {
            chatnameBinding.viewmodel?.chatroom?.value = content[position]
            if (chatnameBinding.viewmodel?.chatroom?.value?.avatar != null) {
                val byteArray = chatnameBinding.viewmodel?.chatroom?.value?.avatar
                val options = BitmapFactory.Options()
                options.inSampleSize = 3 // 将inSampleSize设置为3，表示将图像尺寸缩小为原来的1/3
                val bitmap =
                    byteArray?.let { BitmapFactory.decodeByteArray(byteArray, 0, it.size, options) }
                chatnameBinding.ivChatNameAvator.setImageBitmap(bitmap)
            } else {
                val defaultImageResId = R.drawable.noimg // 預設圖片
                chatnameBinding.ivChatNameAvator.setImageResource(defaultImageResId) // 設置預設圖片
            }
//            Log.d(myTag, "cardvalue: ${chatnameBinding.viewmodel?.chatroom?.value}")
            val bundle = Bundle()
            bundle.putSerializable("nickname", content[position])
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_chatRoomFragment_to_chatFragment, bundle)
            }
        }
    }
}