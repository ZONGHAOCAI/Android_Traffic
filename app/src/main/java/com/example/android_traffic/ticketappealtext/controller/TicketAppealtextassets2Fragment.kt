package com.example.android_traffic.ticketappealtext.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.android_traffic.R
import com.example.android_traffic.databinding.FragmentTicketAppealtextassets2Binding
import com.example.android_traffic.ticketappealtext.viewmodel.TicketAppealtextassets2ViewModel

class TicketAppealtextassets2Fragment : Fragment() {



    private lateinit var binding: FragmentTicketAppealtextassets2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: TicketAppealtextassets2ViewModel by viewModels()
        binding = FragmentTicketAppealtextassets2Binding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            val inputStream1 =requireContext().assets.open("tttsecond")
            inputStream1.bufferedReader().useLines {
                    lines->
                val text =lines.fold(""){accum,line->"$accum$line\n"}
                viewModel?.ttthing2?.value=text

            }
            tttnook2.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.ticketAppealtext2Fragment)
            }

        }
    }
}