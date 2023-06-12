package com.example.android_traffic.ticket.controller

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android_traffic.core.model.Ticket
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
                binding.viewmodel?.content?.value = it as Ticket
                if (it.appendix != null) {
                    var count = 0
                    for (i in (it.appendix)!!) {
                        var byteArray = i
                        val options = BitmapFactory.Options()
                        options.inSampleSize = 3 // 将inSampleSize设置为3，表示将图像尺寸缩小为原来的1/3
                        val bitmap =
                            BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size, options)
                        when (count) {
                            0 -> binding.ivTicketHistoryContentThumbnail1.setImageBitmap(bitmap)
                            1 -> binding.ivTicketHistoryContentThumbnail2.setImageBitmap(bitmap)
                            2 -> binding.ivTicketHistoryContentThumbnail3.setImageBitmap(bitmap)
                            3 -> binding.ivTicketHistoryContentThumbnail4.setImageBitmap(bitmap)
                            4 -> binding.ivTicketHistoryContentThumbnail5.setImageBitmap(bitmap)
                        }
                        count++
                    }
                }
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