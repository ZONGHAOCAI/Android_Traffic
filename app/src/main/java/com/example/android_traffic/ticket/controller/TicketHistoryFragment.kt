package com.example.android_traffic.ticket.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_traffic.R
import com.example.android_traffic.ticket.viewmodel.TicketHistoryViewModel

class TicketHistoryFragment : Fragment() {

    companion object {
        fun newInstance() = TicketHistoryFragment()
    }

    private lateinit var viewModel: TicketHistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ticket_history, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TicketHistoryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}