package com.example.android_traffic.ticketappealtext.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.android_traffic.MainActivity
import com.example.android_traffic.R
import com.example.android_traffic.databinding.FragmentTicketAppealtext2Binding
import com.example.android_traffic.ticketappealtext.viewmodel.TicketAppealtext2ViewModel

class TicketAppealtext2Fragment : Fragment() {


    private lateinit var binding: FragmentTicketAppealtext2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: TicketAppealtext2ViewModel by viewModels()
        binding = FragmentTicketAppealtext2Binding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {

            tttbutton1.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.ticketAppealtextassetsFragment)
            }
            tttbutton2.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.ticketAppealtextassets2Fragment)
            }
            tttido.setOnClickListener {
                if (tttyes.isChecked) {
                    Navigation.findNavController(it).navigate(R.id.ticketAppealtextFragment)
                    ttterro.text = ""
                } else {
                    val errorMessage = "請勾選並確認已閱讀網路服務條款和個資隱私條款"
                    ttterro.text = errorMessage
                }

            }

        }
    }
}