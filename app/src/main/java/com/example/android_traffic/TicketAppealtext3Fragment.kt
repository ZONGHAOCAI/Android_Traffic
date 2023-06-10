package com.example.android_traffic

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class TicketAppealtext3Fragment : Fragment() {

    companion object {
        fun newInstance() = TicketAppealtext3Fragment()
    }

    private lateinit var viewModel: TicketAppealtext3ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ticket_appealtext3, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TicketAppealtext3ViewModel::class.java)
        // TODO: Use the ViewModel
    }

}