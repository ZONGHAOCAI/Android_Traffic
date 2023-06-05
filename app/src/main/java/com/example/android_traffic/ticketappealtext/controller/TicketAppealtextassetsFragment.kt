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
import com.example.android_traffic.databinding.FragmentTicketAppealtextassetsBinding
import com.example.android_traffic.ticketappealtext.viewmodel.TicketAppealtextassetsViewModel

class TicketAppealtextassetsFragment : Fragment() {


    private lateinit var binding: FragmentTicketAppealtextassetsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
val viewModel:TicketAppealtextassetsViewModel by viewModels()
        binding = FragmentTicketAppealtextassetsBinding.inflate(inflater,container,false)
        binding.viewModel=viewModel
        binding.lifecycleOwner=this
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
val inputStream1 =requireContext().assets.open("tttfist")
inputStream1.bufferedReader().useLines {
lines->
    val text =lines.fold(""){accum,line->"$accum$line\n"}
    viewModel?.ttthing1?.value=text

}
tttnook.setOnClickListener {
Navigation.findNavController(it).navigate(R.id.ticketAppealtext2Fragment)
}

        }
    }

}