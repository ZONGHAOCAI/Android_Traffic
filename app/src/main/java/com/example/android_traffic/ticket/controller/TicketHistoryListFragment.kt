package com.example.android_traffic.ticket.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_traffic.R
import com.example.android_traffic.databinding.FragmentTicketHistoryBinding
import com.example.android_traffic.databinding.FragmentTicketHistoryListBinding
import com.example.android_traffic.databinding.FragmentTicketUnpaidListBinding
import com.example.android_traffic.ticket.model.Token
import com.example.android_traffic.ticket.viewmodel.TicketHistoryListViewModel
import com.example.android_traffic.ticket.viewmodel.TicketUnpaidListViewModel

class TicketHistoryListFragment : Fragment() {

    private lateinit var binding: FragmentTicketHistoryListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewmodel: TicketHistoryListViewModel by viewModels()
        binding = FragmentTicketHistoryListBinding.inflate(inflater, container, false)
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this
        return binding.root

//        super.onCreateView(inflater, container, savedInstanceState)
//        activity?.setTitle("1")
//        binding = FragmentTicketListBinding.inflate(inflater, container, false)
//        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            loadPreferences()
            rvTicketHistoryListTicket.layoutManager = LinearLayoutManager(requireContext())
            viewmodel?.init()
            viewmodel?.list?.observe(viewLifecycleOwner) {
                // adapter為null要建立新的adapter
                if (rvTicketHistoryListTicket.adapter == null) {
                    rvTicketHistoryListTicket.adapter = TicketHistoryAdapter(it)
                } else {
                    (rvTicketHistoryListTicket.adapter as TicketHistoryAdapter).updateTicketHistoryList(it)
                }
            }

            svTicketHistoryListSearch.setOnQueryTextListener(object :
                SearchView.OnQueryTextListener {
                // 輸入的文字改變時呼叫
                override fun onQueryTextChange(newText: String?): Boolean {
                    viewmodel?.search(newText)
                    return true
                }

                // 點擊虛擬鍵盤上的提交鈕時呼叫
                override fun onQueryTextSubmit(text: String): Boolean {
                    return false
                }
            })
            //            viewmodel?.getNewTicket()
        }
    }

    private fun loadPreferences() {
        with(binding) {
            val preferences = Token().getEncryptedPreferences(requireContext())
            viewmodel?.member?.value = preferences.getString("MemId","")
        }
    }
}