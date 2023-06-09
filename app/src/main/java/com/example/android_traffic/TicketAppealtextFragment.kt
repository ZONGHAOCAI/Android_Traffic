package com.example.android_traffic

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class TicketAppealtextFragment : Fragment() {

    companion object {
        fun newInstance() = TicketAppealtextFragment()
    }

    private lateinit var viewModel: TicketAppealtextViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ticket_appealtext, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TicketAppealtextViewModel::class.java)
        // TODO: Use the ViewModel
    }

}