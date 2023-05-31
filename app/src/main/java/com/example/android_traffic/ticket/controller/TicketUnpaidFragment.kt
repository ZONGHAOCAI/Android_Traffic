package com.example.android_traffic.ticket.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_traffic.R
import com.example.android_traffic.ticket.viewmodel.TicketUnpaidViewModel

class TicketUnpaidFragment : Fragment() {

    companion object {
        fun newInstance() = TicketUnpaidFragment()
    }

    private lateinit var viewModel: TicketUnpaidViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ticket_unpaid, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TicketUnpaidViewModel::class.java)
        // TODO: Use the ViewModel
    }

}