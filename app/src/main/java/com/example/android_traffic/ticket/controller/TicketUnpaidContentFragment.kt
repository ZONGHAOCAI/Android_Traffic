package com.example.android_traffic.ticket.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android_traffic.databinding.FragmentTicketUnpaidContentBinding
import com.example.android_traffic.ticket.model.Content
import com.example.android_traffic.ticket.viewmodel.TicketUnpaidContentViewModel

class TicketUnpaidContentFragment : Fragment() {

    private lateinit var binding: FragmentTicketUnpaidContentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewmodel: TicketUnpaidContentViewModel by viewModels()
        binding = FragmentTicketUnpaidContentBinding.inflate(inflater, container, false)
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
            ivTicketUnpaidContentThumbnail1.setOnClickListener {
                ivTicketUnpaidContentPicture.setImageDrawable(ivTicketUnpaidContentThumbnail1.drawable)
                ivTicketUnpaidContentPicture.visibility = View.VISIBLE
            }
            ivTicketUnpaidContentThumbnail2.setOnClickListener {
                ivTicketUnpaidContentPicture.setImageDrawable(ivTicketUnpaidContentThumbnail2.drawable)
                ivTicketUnpaidContentPicture.visibility = View.VISIBLE
            }
            ivTicketUnpaidContentThumbnail3.setOnClickListener {
                ivTicketUnpaidContentPicture.setImageDrawable(ivTicketUnpaidContentThumbnail3.drawable)
                ivTicketUnpaidContentPicture.visibility = View.VISIBLE
            }
            ivTicketUnpaidContentThumbnail4.setOnClickListener {
                ivTicketUnpaidContentPicture.setImageDrawable(ivTicketUnpaidContentThumbnail4.drawable)
                ivTicketUnpaidContentPicture.visibility = View.VISIBLE
            }
            ivTicketUnpaidContentThumbnail5.setOnClickListener {
                ivTicketUnpaidContentPicture.setImageDrawable(ivTicketUnpaidContentThumbnail5.drawable)
                ivTicketUnpaidContentPicture.visibility = View.VISIBLE
            }
        }
    }
}