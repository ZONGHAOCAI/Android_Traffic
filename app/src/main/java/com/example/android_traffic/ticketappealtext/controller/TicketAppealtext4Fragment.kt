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
import com.example.android_traffic.databinding.FragmentTicketAppealtext4Binding
import com.example.android_traffic.ticketappealtext.viewmodel.TicketAppealtext4ViewModel

class TicketAppealtext4Fragment : Fragment() {


    private lateinit var binding: FragmentTicketAppealtext4Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel :TicketAppealtext4ViewModel by viewModels()
        binding = FragmentTicketAppealtext4Binding.inflate(inflater,container,false)
        binding.viewModel=viewModel
        binding.lifecycleOwner=this
        return binding.root
    }
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
       tttover.setOnClickListener {
           Navigation.findNavController(it).navigate(R.id.ticketUnpaidContentFragment)

       }
        }


        }


}