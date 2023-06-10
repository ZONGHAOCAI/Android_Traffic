package com.example.android_traffic.ticketappealtext.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.android_traffic.R
import com.example.android_traffic.databinding.FragmentTicketAppealtextBinding

import com.example.android_traffic.ticketappealtext.viewmodel.TicketAppealtextViewModel

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
            goback.setOnClickListener {
                findNavController().popBackStack()
            }
            gonext.setOnClickListener {
                if (!inputValid()) {
                    return@setOnClickListener
                }
                val bundle = Bundle()
                val spinner = binding.tttmenu
                val select = spinner.selectedItem.toString()
                val con = viewModel!!.con.value
                val contwo = viewModel?.contwo?.value
                bundle.putString("select", select)
                bundle.putString("con", con)
                bundle.putString("contwo", contwo)

               Navigation.findNavController(it).navigate(R.id.ticketAppealtext3Fragment,bundle)
            }
        }
    }


    private fun inputValid(): Boolean {
        var valid = true
        with(binding) {

            val con = viewModel?.con?.value?.trim()
            val contwo = viewModel?.contwo?.value?.trim()
            if (con == null || con.isEmpty()) {
                tttcon.error = "內容不可空白"
                valid = false
            }
        }
        return valid
    }
}

