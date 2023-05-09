package com.example.android_traffic

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FineAppeal2Fragment : Fragment() {

    companion object {
        fun newInstance() = FineAppeal2Fragment()
    }

    private lateinit var viewModel: FineAppeal2ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fine_appeal2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FineAppeal2ViewModel::class.java)
        // TODO: Use the ViewModel
    }

}