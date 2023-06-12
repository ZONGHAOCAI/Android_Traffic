package com.example.android_traffic.ticket.controller

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_traffic.databinding.FragmentTicketUnpaidListBinding
import com.example.android_traffic.ticket.model.Token
import com.example.android_traffic.ticket.viewmodel.TicketUnpaidListViewModel

class TicketUnpaidListFragment : Fragment() {

    private lateinit var binding: FragmentTicketUnpaidListBinding
//    val myTag = "TAG_${javaClass.simpleName}"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewmodel: TicketUnpaidListViewModel by viewModels()
        binding = FragmentTicketUnpaidListBinding.inflate(inflater, container, false)
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
//            (requireActivity() as MainActivity).setSupportActionBar(toolbar)
//            toolbar.setTitle("未繳費")
            loadPreferences()
            rvTicketUnpaidListTicket.layoutManager = LinearLayoutManager(requireContext())
            viewmodel?.init()
            viewmodel?.list?.observe(viewLifecycleOwner) {
//                rvTicketUnpaidListTicket.adapter = TicketUnpaidAdapter(it)
                // adapter為null要建立新的adapter
                if (rvTicketUnpaidListTicket.adapter == null) {
                    rvTicketUnpaidListTicket.adapter = TicketUnpaidAdapter(it)
                } else {
                    (rvTicketUnpaidListTicket.adapter as TicketUnpaidAdapter).updateTicketUnpaidList(
                        it
                    )
                }
            }

            svTicketUnpaidListSearch.setOnQueryTextListener(object :
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
//            Log.d(myTag, "getString: ${preferences.getString("MemId","")?.javaClass?.simpleName}")
        }
    }
}