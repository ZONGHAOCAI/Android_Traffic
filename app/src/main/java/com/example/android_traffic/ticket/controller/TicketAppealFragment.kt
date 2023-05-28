package com.example.android_traffic.ticket.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_traffic.R
import com.example.android_traffic.ticket.viewmodel.TicketAppealViewModel

class TicketAppealFragment : Fragment() {

    companion object {
        fun newInstance() = TicketAppealFragment()
    }

    private lateinit var viewModel: TicketAppealViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ticket_appeal, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TicketAppealViewModel::class.java)
        // TODO: Use the ViewModel
    }

}