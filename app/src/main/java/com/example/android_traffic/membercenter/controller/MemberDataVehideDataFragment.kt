package com.example.android_traffic.membercenter.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_traffic.R
import com.example.android_traffic.databinding.FragmentMemberDataVehideDataBinding
import com.example.android_traffic.membercenter.adapter.MemberDataVehideDataAdapter
import com.example.android_traffic.membercenter.viewmodel.MemberDataVehideDataViewModel

class MemberDataVehideDataFragment : Fragment() {
    private lateinit var binding: FragmentMemberDataVehideDataBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: MemberDataVehideDataViewModel by viewModels()
        binding = FragmentMemberDataVehideDataBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            viewModel!!.init()
            rvMemberDataVehideData.layoutManager = LinearLayoutManager(requireContext())
            viewModel!!.vehideDataList.observe(viewLifecycleOwner){
                rvMemberDataVehideData.adapter = MemberDataVehideDataAdapter(it)
            }
        }
    }



}