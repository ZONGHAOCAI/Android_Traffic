package com.example.android_traffic

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class TicketAppealtextassets2Fragment : Fragment() {

    companion object {
        fun newInstance() = TicketAppealtextassets2Fragment()
    }

    private lateinit var viewModel: TicketAppealtextassets2ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ticket_appealtextassets2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TicketAppealtextassets2ViewModel::class.java)
        // TODO: Use the ViewModel
    }

}