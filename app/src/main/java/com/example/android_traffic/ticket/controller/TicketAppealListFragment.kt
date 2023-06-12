package com.example.android_traffic.ticket.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_traffic.databinding.FragmentTicketAppealListBinding
import com.example.android_traffic.ticket.model.Token
import com.example.android_traffic.ticket.viewmodel.TicketAppealListViewModel

class TicketAppealListFragment : Fragment() {
    private lateinit var binding: FragmentTicketAppealListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewmodel: TicketAppealListViewModel by viewModels()
        binding = FragmentTicketAppealListBinding.inflate(inflater, container, false)
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            loadPreferences()
            rvTicketAppealListTicket.layoutManager = LinearLayoutManager(requireContext())
            viewmodel?.init()
            viewmodel?.list?.observe(viewLifecycleOwner) {
                // adapter為null要建立新的adapter
                if (rvTicketAppealListTicket.adapter == null) {
                    rvTicketAppealListTicket.adapter = TicketAppealAdapter(it)
                } else {
                    (rvTicketAppealListTicket.adapter as TicketAppealAdapter).updateTicketAppealList(
                        it
                    )
                }
            }

            svTicketAppealListSearch.setOnQueryTextListener(object :
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
            viewmodel?.member?.value = preferences.getString("MemId", "")
        }
    }
}