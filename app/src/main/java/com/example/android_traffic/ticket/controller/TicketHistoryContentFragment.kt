package com.example.android_traffic.ticket.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android_traffic.databinding.FragmentTicketHistoryContentBinding
import com.example.android_traffic.ticket.model.Content
import com.example.android_traffic.ticket.viewmodel.TicketHistoryContentViewModel

class TicketHistoryContentFragment : Fragment() {

    private lateinit var binding: FragmentTicketHistoryContentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewmodel: TicketHistoryContentViewModel by viewModels()
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
        with(binding) {
            ivTicketHistoryContentThumbnail1.setOnClickListener {
                ivTicketHistoryContentPicture.setImageDrawable(ivTicketHistoryContentThumbnail1.drawable)
                ivTicketHistoryContentPicture.visibility = View.VISIBLE
            }
            ivTicketHistoryContentThumbnail2.setOnClickListener {
                ivTicketHistoryContentPicture.setImageDrawable(ivTicketHistoryContentThumbnail2.drawable)
                ivTicketHistoryContentPicture.visibility = View.VISIBLE
            }
            ivTicketHistoryContentThumbnail3.setOnClickListener {
                ivTicketHistoryContentPicture.setImageDrawable(ivTicketHistoryContentThumbnail3.drawable)
                ivTicketHistoryContentPicture.visibility = View.VISIBLE
            }
            ivTicketHistoryContentThumbnail4.setOnClickListener {
                ivTicketHistoryContentPicture.setImageDrawable(ivTicketHistoryContentThumbnail4.drawable)
                ivTicketHistoryContentPicture.visibility = View.VISIBLE
            }
            ivTicketHistoryContentThumbnail5.setOnClickListener {
                ivTicketHistoryContentPicture.setImageDrawable(ivTicketHistoryContentThumbnail5.drawable)
                ivTicketHistoryContentPicture.visibility = View.VISIBLE
            }
            ivTicketHistoryContentPicture.setOnLongClickListener {
                ivTicketHistoryContentPicture.visibility = View.GONE
                true
            }
        }
    }
}