package com.example.android_traffic

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class WhistleblowerForm3Fragment : Fragment() {

    companion object {
        fun newInstance() = WhistleblowerForm3Fragment()
    }

    private lateinit var viewModel: WhistleblowerForm3ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_whistleblower_form3, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WhistleblowerForm3ViewModel::class.java)
        // TODO: Use the ViewModel
    }

}