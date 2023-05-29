package com.example.android_traffic.ticket.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.android_traffic.R
import com.example.android_traffic.databinding.FragmentTicketHistoryContentBinding
import com.example.android_traffic.databinding.FragmentTicketUnpaidContentBinding
import com.example.android_traffic.ticket.model.Content
import com.example.android_traffic.ticket.viewmodel.TicketHistoryContentViewModel
import com.example.android_traffic.ticket.viewmodel.TicketUnpaidContentViewModel

class TicketHistoryContentFragment : Fragment() {

    private lateinit var binding: FragmentTicketHistoryContentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewmodel : TicketHistoryContentViewModel by viewModels()
        binding = FragmentTicketHistoryContentBinding.inflate(inflater, container, false)
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            it.getSerializable("number")?.let {
                binding.viewmodel?.content?.value = it as Content
            }
        }
    }
}