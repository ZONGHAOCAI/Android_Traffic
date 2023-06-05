package com.example.android_traffic.chat.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_traffic.MainActivity
import com.example.android_traffic.chat.Util.ChatMessage
import com.example.android_traffic.chat.model.ChatPartner
import com.example.android_traffic.chat.viewmodel.ChatViewModel
import com.example.android_traffic.databinding.FragmentChatBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class ChatFragment : Fragment() {
    private lateinit var binding: FragmentChatBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var storage: FirebaseStorage
    private lateinit var message: MutableList<ChatMessage>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance()
        message = mutableListOf()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 將標題隱藏
        (requireActivity() as MainActivity).supportActionBar?.hide()
        val viewmodel: ChatViewModel by viewModels()
        binding = FragmentChatBinding.inflate(inflater, container, false)
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            it.getSerializable("nickname")?.let {
                binding.viewmodel?.avatar?.value = it as ChatPartner
            }
            with(binding) {
                rvChatContent.layoutManager = LinearLayoutManager(requireContext())
                viewmodel?.chatcontent?.observe(viewLifecycleOwner) {
                    if (rvChatContent.adapter == null) {
                        rvChatContent.adapter = ChatAdapter(it)
//                        這個方法也可以，是以堆疊顯示，但是會有畫面跳轉的畫面，如果資料多會跳很快
//                        rvChatContent.layoutManager = LinearLayoutManager(requireContext()).apply {
//                            stackFromEnd = true
                        rvChatContent.post {
                            rvChatContent.scrollToPosition(it.size - 1)
                        }
                    } else {
                        (rvChatContent.adapter as ChatAdapter).updateChatContentList(it)
                        rvChatContent.post {
                            rvChatContent.smoothScrollToPosition(it.size - 1)
                        }
                    }
                }
            }
        }
    }

    /** 取得所有聊天資訊後顯示  */
//    private fun showAllMessages() {
//        // 依照建立時間新舊排序
//        db.collection("message").orderBy("messageTime", Query.Direction.DESCENDING).get()
//            // 這是個callback
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    task.result?.let { querySnapshot ->
//                        // 先清除舊資料後再儲存新資料
//                        if (message.isNotEmpty()) {
//                            message.clear()
//                        }
//                        for (document in querySnapshot) {
//                            message.add(document.toObject(ChatMessage::class.java))
//                        }
//                        showMessage()
//                    }
//                }
//            }
//    }

//    fun showMessage() {
//        with(binding) {
//            val chatAdapter: ChatAdapter = if (rvChatContent.adapter == null) {
//                ChatAdapter(mutableListOf())
//            } else {
//                rvChatContent.adapter as ChatAdapter
//            }
//            rvChatContent.adapter = chatAdapter
//            chatAdapter.updateChatContentList(message)
//        }
//    }
//
//    override fun onStart() {
//        super.onStart()
//        showAllMessages()
//    }
}