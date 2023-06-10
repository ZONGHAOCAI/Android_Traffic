package com.example.android_traffic.chat.controller

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_traffic.chat.viewmodel.ChatRoomViewModel
import com.example.android_traffic.databinding.FragmentChatRoomBinding
import com.example.android_traffic.ticket.model.Token

class ChatRoomFragment : Fragment() {
    private lateinit var binding: FragmentChatRoomBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewmodel: ChatRoomViewModel by viewModels()
        binding = FragmentChatRoomBinding.inflate(inflater, container, false)
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            loadPreferences()
            rvChatRoomChat.layoutManager = LinearLayoutManager(requireContext())
            viewmodel?.init()
            viewmodel?.list?.observe(viewLifecycleOwner) {
                if (rvChatRoomChat.adapter == null) {
                    rvChatRoomChat.adapter = ChatRoomAdapter(it)
                } else {
                    (rvChatRoomChat.adapter as ChatRoomAdapter).updateChatList(it)
                }
            }

            svChatRoomSearch.setOnQueryTextListener(object :
            // 輸入的文字改變時呼叫
                SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    viewmodel?.search(newText)
                    return true
                }

                // 點擊虛擬鍵盤上的提交鈕時呼叫
                override fun onQueryTextSubmit(text: String): Boolean {
                    return false
                }
            })
            viewmodel?.getNewChatRoom()

//            if () {
//
//            }
        }
    }

    private fun loadPreferences() {
        with(binding) {
            val preferences = Token().getEncryptedPreferences(requireContext())
            viewmodel?.member?.value = preferences.getString("MemId", "")
            val myTag = "TAG_${javaClass.simpleName}"
            Log.d(myTag, "getString: ${preferences.getString("MemId", "")?.javaClass?.simpleName}")
        }
    }
}