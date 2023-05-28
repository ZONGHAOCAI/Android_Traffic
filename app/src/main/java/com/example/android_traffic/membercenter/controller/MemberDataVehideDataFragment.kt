package com.example.android_traffic.membercenter.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_traffic.R
import com.example.android_traffic.membercenter.viewmodel.MemberDataVehideDataViewModel

class MemberDataVehideDataFragment : Fragment() {

    companion object {
        fun newInstance() = MemberDataVehideDataFragment()
    }

    private lateinit var viewModel: MemberDataVehideDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_member_data_vehide_data, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MemberDataVehideDataViewModel::class.java)
        // TODO: Use the ViewModel
    }

}