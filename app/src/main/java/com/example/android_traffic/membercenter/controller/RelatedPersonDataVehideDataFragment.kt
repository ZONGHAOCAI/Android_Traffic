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
import com.example.android_traffic.databinding.FragmentRelatedPersonDataVehideDataBinding
import com.example.android_traffic.membercenter.adapter.RelatedPersonDataVehideDataAdapter
import com.example.android_traffic.membercenter.adapter.RelatedPersonListAdapter
import com.example.android_traffic.membercenter.viewmodel.RelatedPersonDataVehideDataViewModel

class RelatedPersonDataVehideDataFragment : Fragment() {
    private lateinit var binding: FragmentRelatedPersonDataVehideDataBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: RelatedPersonDataVehideDataViewModel by viewModels()
        binding = FragmentRelatedPersonDataVehideDataBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            arguments?.let { bundle ->
                bundle.getSerializable("ID")?.let { ID ->
                    viewModel!!.init(ID as Int)
                }
            }

            rvMemberDataVehideData.layoutManager = LinearLayoutManager(requireContext())
            viewModel!!.vehideDataList.observe(viewLifecycleOwner){
                rvMemberDataVehideData.adapter = RelatedPersonDataVehideDataAdapter(it)
            }
        }
    }


}