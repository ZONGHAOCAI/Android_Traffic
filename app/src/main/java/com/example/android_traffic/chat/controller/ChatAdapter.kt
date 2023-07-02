package com.example.android_traffic.chat.controller

import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.android_traffic.R
import com.example.android_traffic.chat.viewmodel.ChatViewModel
import com.example.android_traffic.core.model.Chat
import com.example.android_traffic.databinding.ChatContentBinding

class ChatAdapter(private var content: List<Chat>) :
    RecyclerView.Adapter<ChatAdapter.ChatHolder>() {
    //    private lateinit var content: List<Chat>
    private val myTag = "TAG_${javaClass.simpleName}"

    companion object {
        private lateinit var preferences: SharedPreferences
    }

    fun updateChatContentList(chatcontent: List<Chat>) {
        this.content = chatcontent
        notifyDataSetChanged() // 更新資料內容
    }

    class ChatHolder(val chatcontentBinding: ChatContentBinding) :
        RecyclerView.ViewHolder(chatcontentBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        val chatcontentBinding =
            ChatContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        chatcontentBinding.viewmodel = ChatViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        chatcontentBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return ChatHolder(chatcontentBinding)
    }

    override fun getItemCount(): Int {
        return content.size
    }

    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        with(holder) {
            chatcontentBinding.viewmodel?.chat?.value = content[position]
            if (chatcontentBinding.viewmodel?.chat?.value?.avatar != null) {
                val byteArray = chatcontentBinding.viewmodel?.chat?.value?.avatar
                val options = BitmapFactory.Options()
                options.inSampleSize = 3 // 將inSampleSize設置為3 = 圖片尺寸縮小為1/3
                val bitmap =
                    byteArray?.let { BitmapFactory.decodeByteArray(byteArray, 0, it.size, options) }
                chatcontentBinding.ivChatContentUserAvatar.setImageBitmap(bitmap)
            } else {
                val defaultImageResId = R.drawable.noimg // 預設圖片
                chatcontentBinding.ivChatContentUserAvatar.setImageResource(defaultImageResId) // 設置預設圖片
            }

            if (chatcontentBinding.viewmodel?.chat?.value?.appendix != null) {
                val byteArray = chatcontentBinding.viewmodel!!.chat!!.value!!.appendix
                val options = BitmapFactory.Options()
                options.inSampleSize = 3 // 將inSampleSize設置為3 = 圖片尺寸縮小為1/3
                val bitmap =
                    byteArray?.let { BitmapFactory.decodeByteArray(byteArray, 0, it.size, options) }
                chatcontentBinding.ivChatContentOtherImg.setImageBitmap(bitmap)
                chatcontentBinding.ivChatContentUserImg.setImageBitmap(bitmap)
            }

            val itemview = content[position]
            val memberid = itemview.senderID
            val photo = itemview.appendix

            val imageViewLeft =
                holder.itemView.findViewById<ImageView>(R.id.iv_ChatContent_OtherImg)
            val imageViewRight =
                holder.itemView.findViewById<ImageView>(R.id.iv_ChatContent_UserImg)
            val TextViewLeft = holder.itemView.findViewById<TextView>(R.id.tv_ChatContent_OtherText)
            val TextViewRight = holder.itemView.findViewById<TextView>(R.id.tv_ChatContent_UserText)
            val Left = holder.itemView.findViewById<RelativeLayout>(R.id.rl_ChatContent_Left)
            val Right = holder.itemView.findViewById<RelativeLayout>(R.id.rl_ChatContent_Right)

            if (memberid != getPreferenceValue().toInt()) {
                Right.visibility = View.GONE
                Left.visibility = View.VISIBLE
            } else {
                Right.visibility = View.VISIBLE
                Left.visibility = View.GONE
            }
            if (photo != null) {
                TextViewLeft.visibility = View.GONE
                TextViewRight.visibility = View.GONE
                imageViewLeft.visibility = View.VISIBLE
                imageViewRight.visibility = View.VISIBLE
            }else{
                TextViewLeft.visibility = View.VISIBLE
                TextViewRight.visibility = View.VISIBLE
                imageViewLeft.visibility = View.GONE
                imageViewRight.visibility = View.GONE
            }
        }
    }

    fun setSharedPreferences(prefs: SharedPreferences) {
        preferences = prefs
    }

    private fun getPreferenceValue(): String {
        return preferences.getString("MemId", "") ?: ""
    }
}


