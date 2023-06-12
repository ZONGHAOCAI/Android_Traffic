package com.example.android_traffic.ticket.controller

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.android_traffic.MainActivityViewModel
import com.example.android_traffic.R
import com.example.android_traffic.TapPay
import com.example.android_traffic.core.model.Ticket
import com.example.android_traffic.databinding.FragmentTicketUnpaidContentBinding
import com.example.android_traffic.ticket.viewmodel.TicketUnpaidContentViewModel

class TicketUnpaidContentFragment : Fragment() {
    private lateinit var binding: FragmentTicketUnpaidContentBinding
    private lateinit var activityViewModel: MainActivityViewModel
    val myTag = "TAG_${javaClass.simpleName}"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewmodel: TicketUnpaidContentViewModel by viewModels()
        binding = FragmentTicketUnpaidContentBinding.inflate(inflater, container, false)
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this
        //綁定activity的viewModel
        activityViewModel = ViewModelProvider(requireActivity())[MainActivityViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            activityViewModel.resultLiveData.observe(viewLifecycleOwner) {
                if (it) {
                    Toast.makeText(context, "繳納成功", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack(R.id.mainFragment, false)
                }
            }
            arguments?.let {
                it.getSerializable("number")?.let {
                    binding.viewmodel?.content?.value = it as Ticket
                    Log.d(myTag, "recycleview: ${binding.viewmodel?.content?.value}")
                    if (it.appendix != null) {
                        var count = 0
                        for (i in (it.appendix)!!) {
                            var byteArray = i
                            val options = BitmapFactory.Options()
                            options.inSampleSize = 3 // 将inSampleSize设置为3，表示将图像尺寸缩小为原来的1/3
                            val bitmap =
                                BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size, options)
                            when (count) {
                                0 -> binding.ivTicketUnpaidContentThumbnail1.setImageBitmap(bitmap)
                                1 -> binding.ivTicketUnpaidContentThumbnail2.setImageBitmap(bitmap)
                                2 -> binding.ivTicketUnpaidContentThumbnail3.setImageBitmap(bitmap)
                                3 -> binding.ivTicketUnpaidContentThumbnail4.setImageBitmap(bitmap)
                                4 -> binding.ivTicketUnpaidContentThumbnail5.setImageBitmap(bitmap)
                            }
                            count++
                        }
                    }
                }
            }

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
            ivTicketUnpaidContentPicture.setOnLongClickListener {
                ivTicketUnpaidContentPicture.visibility = View.GONE
                true
            }
            btTicketUnpaidContentPayTicket.setOnClickListener {
                TapPay.getInstance().prepareGooglePay(
                    requireActivity(),
                    101101,
                    binding.viewmodel?.content?.value?.amount!!,
                    binding.viewmodel?.content?.value?.phoneNo!!,
                    binding.viewmodel?.content?.value?.vehicleNo!!
                )
            }
            btTicketUnpaidContentAppeal.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.ticketAppealtext2Fragment)
            }
        }
    }
}