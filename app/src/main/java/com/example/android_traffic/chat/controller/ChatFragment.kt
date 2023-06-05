package com.example.android_traffic.chat.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_traffic.chat.model.ChatPartner
import com.example.android_traffic.chat.viewmodel.ChatViewModel
import com.example.android_traffic.databinding.FragmentChatBinding

class ChatFragment : Fragment() {
    private lateinit var binding: FragmentChatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewmodel : ChatViewModel by viewModels()
        binding = FragmentChatBinding.inflate(inflater, container, false)
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            it.getSerializable("nickname")?.let {
                binding.viewmodel?.avatar?.value = it as ChatPartner
            }
            with(binding) {
                rvChatContent.layoutManager = LinearLayoutManager(requireContext())
                viewmodel?.chatcontent?.observe(viewLifecycleOwner) {
                    if (rvChatContent.adapter == null) {
                        rvChatContent.adapter = ChatAdapter(it)
                    } else {
                        (rvChatContent.adapter as ChatAdapter).updateChatContentList(it)
                    }
                }
            }
        }
    }
}