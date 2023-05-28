package com.example.android_traffic.chat.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_traffic.R
import com.example.android_traffic.chat.viewmodel.ChatRoomViewModel
import com.example.android_traffic.databinding.FragmentChatRoomBinding

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
        with(binding) {
            rvChatRoomChat.layoutManager = LinearLayoutManager(requireContext())
            viewmodel?.content?.observe(viewLifecycleOwner) {

                if (rvChatRoomChat.adapter == null) {
                    rvChatRoomChat.adapter = ChatAdapter(it)
                } else {
                    (rvChatRoomChat.adapter as ChatAdapter).updateChatList(it)
                }
            }
        }
    }
}