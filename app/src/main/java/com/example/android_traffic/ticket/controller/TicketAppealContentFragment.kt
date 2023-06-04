package com.example.android_traffic.ticket.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.android_traffic.R
import com.example.android_traffic.databinding.FragmentTicketAppealContentBinding
import com.example.android_traffic.databinding.FragmentTicketHistoryContentBinding
import com.example.android_traffic.ticket.model.Content
import com.example.android_traffic.ticket.viewmodel.TicketAppealContentViewModel
import com.example.android_traffic.ticket.viewmodel.TicketHistoryContentViewModel

class TicketAppealContentFragment : Fragment() {

    private lateinit var binding: FragmentTicketAppealContentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewmodel: TicketAppealContentViewModel by viewModels()
        binding = FragmentTicketAppealContentBinding.inflate(inflater, container, false)
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
            ivTicketAppealContentThumbnail1.setOnClickListener {
                ivTicketAppealContentPicture.setImageDrawable(ivTicketAppealContentThumbnail1.drawable)
                ivTicketAppealContentPicture.visibility = View.VISIBLE
            }
            ivTicketAppealContentThumbnail2.setOnClickListener {
                ivTicketAppealContentPicture.setImageDrawable(ivTicketAppealContentThumbnail2.drawable)
                ivTicketAppealContentPicture.visibility = View.VISIBLE
            }
            ivTicketAppealContentThumbnail3.setOnClickListener {
                ivTicketAppealContentPicture.setImageDrawable(ivTicketAppealContentThumbnail3.drawable)
                ivTicketAppealContentPicture.visibility = View.VISIBLE
            }
            ivTicketAppealContentThumbnail4.setOnClickListener {
                ivTicketAppealContentPicture.setImageDrawable(ivTicketAppealContentThumbnail4.drawable)
                ivTicketAppealContentPicture.visibility = View.VISIBLE
            }
            ivTicketAppealContentThumbnail5.setOnClickListener {
                ivTicketAppealContentPicture.setImageDrawable(ivTicketAppealContentThumbnail5.drawable)
                ivTicketAppealContentPicture.visibility = View.VISIBLE
            }
            ivTicketAppealContentPicture.setOnLongClickListener {
                ivTicketAppealContentPicture.visibility = View.GONE
                true
            }
        }
    }
}