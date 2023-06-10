package com.example.android_traffic.ticketappealtext.controller

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.android_traffic.R
import com.example.android_traffic.core.service.Server.Companion.urlFindappeal
import com.example.android_traffic.core.service.requestTask
import com.example.android_traffic.databinding.FragmentTicketAppealtextBinding

import com.example.android_traffic.ticketappealtext.viewmodel.TicketAppealtextViewModel
import com.google.gson.JsonObject

class TicketAppealtextFragment : Fragment() {


    private lateinit var binding: FragmentTicketAppealtextBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: TicketAppealtextViewModel by viewModels()
        binding = FragmentTicketAppealtextBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
        arguments?.let {bundle ->
            bundle.getSerializable("ticketNo")?.let {
                viewModel?.appeal?.value?.ticketNo = it as String //接收前面傳的罰單編號
            }
        }
            gonext.setOnClickListener {

                viewModel?.appeal?.value?.reason = "================下拉內容"

                val respBody = requestTask<JsonObject>(
                    urlFindappeal, "POST", viewModel?.appeal?.value
                )
                respBody?.run {
                    if (get("successful").asBoolean){
                        Navigation.findNavController(it).navigate(R.id.ticketContentFragment)
                        Toast.makeText(requireContext(), "申訴成功", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }

    private fun inputValid(): Boolean {
        var valid = true
        with(binding) {


        }
        return valid
    }
}

