package com.example.android_traffic.membercenter.controller


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.android_traffic.databinding.FragmentRelatedPersonBinding
import com.example.android_traffic.membercenter.viewmodel.RelatedPersonViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_traffic.membercenter.adapter.RelatedPersonListAdapter

class RelatedPersonFragment : Fragment() {
    private lateinit var binding: FragmentRelatedPersonBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: RelatedPersonViewModel by viewModels()
        binding = FragmentRelatedPersonBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){

//          recyclerView一定要設定 LinearLayoutManager 不然不會顯示東西
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            viewModel?.relatedPerson?.observe(viewLifecycleOwner) { relatedPerson ->
                // adapter為null要建立新的adapter；之後只要呼叫updateFriends(friends)即可
                if (recyclerView.adapter == null) {
                    recyclerView.adapter = RelatedPersonListAdapter(relatedPerson)
                } else {
                    (recyclerView.adapter as RelatedPersonListAdapter).updateRelatedPerson(relatedPerson)
                }
            }
        }
    }




}