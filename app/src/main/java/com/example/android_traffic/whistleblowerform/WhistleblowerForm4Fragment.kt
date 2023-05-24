package com.example.android_traffic.whistleblowerform

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_traffic.R

class WhistleblowerForm4Fragment : Fragment() {

    companion object {
        fun newInstance() = WhistleblowerForm4Fragment()
    }

    private lateinit var viewModel: WhistleblowerForm4ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_whistleblower_form4, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WhistleblowerForm4ViewModel::class.java)
        // TODO: Use the ViewModel
    }

}