package com.example.android_traffic

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class WhistleblowerForm5Fragment : Fragment() {

    companion object {
        fun newInstance() = WhistleblowerForm5Fragment()
    }

    private lateinit var viewModel: WhistleblowerForm5ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_whistleblower_form5, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WhistleblowerForm5ViewModel::class.java)
        // TODO: Use the ViewModel
    }

}