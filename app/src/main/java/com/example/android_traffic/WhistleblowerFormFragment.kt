package com.example.android_traffic

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class WhistleblowerFormFragment : Fragment() {

    companion object {
        fun newInstance() = WhistleblowerFormFragment()
    }

    private lateinit var viewModel: WhistleblowerFormViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_whistleblower_form, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WhistleblowerFormViewModel::class.java)
        // TODO: Use the ViewModel
    }

}