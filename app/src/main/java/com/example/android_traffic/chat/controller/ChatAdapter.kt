package com.example.android_traffic.chat.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.android_traffic.R
import com.example.android_traffic.chat.model.ChatContent
import com.example.android_traffic.chat.viewmodel.ChatViewModel
import com.example.android_traffic.databinding.ChatContentBinding

class ChatAdapter(private var content: List<ChatContent>) :
    RecyclerView.Adapter<ChatAdapter.Holder>() {
//    class ChatAdapter(private var content: MutableList<ChatMessage>) :
//        RecyclerView.Adapter<ChatAdapter.Holder>() {
//    fun updateChatContentList(chatcontent: MutableList<ChatMessage>) {
//        this.content = chatcontent
//        notifyDataSetChanged() // 更新資料內容
//    }

    fun updateChatContentList(chatcontent: List<ChatContent>) {
        this.content = chatcontent
        notifyDataSetChanged() // 更新資料內容
    }

    class Holder(val chatContentBinding: ChatContentBinding) :
        RecyclerView.ViewHolder(chatContentBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val chatContentBinding =
            ChatContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        chatContentBinding.viewmodel = ChatViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        chatContentBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return Holder(chatContentBinding)
    }

    override fun getItemCount(): Int {
        return content.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val message = content[position]
        with(holder) {
            with(chatContentBinding){
//                 將欲顯示的message物件指派給LiveData，就會自動更新layout檔案的view顯示
                viewmodel?.chatext?.value = message
//                viewmodel?.message?.value = message
            }
            val itemview = message
            val memberid = itemview.MemberID
            val photo = itemview.photo

            val imageViewLeft = holder.itemView.findViewById<ImageView>(R.id.iv_ChatContent_OtherImg)
            val imageViewRight = holder.itemView.findViewById<ImageView>(R.id.iv_ChatContent_UserImg)
            val TextViewLeft = holder.itemView.findViewById<TextView>(R.id.tv_ChatContent_OtherText)
            val TextViewRight = holder.itemView.findViewById<TextView>(R.id.tv_ChatContent_UserText)
            val Left = holder.itemView.findViewById<RelativeLayout>(R.id.rl_ChatContent_Left)
            val Right = holder.itemView.findViewById<RelativeLayout>(R.id.rl_ChatContent_Right)

            if (memberid == 2) {
                Right.visibility = View.GONE
                Left.visibility = View.VISIBLE
            }else{
                Right.visibility = View.VISIBLE
                Left.visibility = View.GONE
            }
            if (photo != null) {
                TextViewLeft.visibility = View.GONE
                TextViewRight.visibility = View.GONE
                imageViewLeft.visibility = View.VISIBLE
                imageViewRight.visibility = View.VISIBLE
            }
        }
    }
}


