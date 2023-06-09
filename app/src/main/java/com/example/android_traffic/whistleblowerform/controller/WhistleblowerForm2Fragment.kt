package com.example.android_traffic.whistleblowerform.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.android_traffic.R
import com.example.android_traffic.databinding.FragmentWhistleblowerForm2Binding
import com.example.android_traffic.whistleblowerform.model.Whistleblower
import com.example.android_traffic.whistleblowerform.viewModel.WhistleblowerForm2ViewModel

class WhistleblowerForm2Fragment : Fragment() {
    private lateinit var binding: FragmentWhistleblowerForm2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWhistleblowerForm2Binding.inflate(inflater, container, false)
        val viewModel : WhistleblowerForm2ViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            arguments?.let {
                viewModel?.whistleblower?.value = it.getSerializable("whistleblower") as Whistleblower?
                println(it)
            }

            //回上一頁
            btnPreviousWhistleblower2.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }

            //送出
            btnNextWhistleblower2.setOnClickListener {
                binding.viewModel?.addWhistleblower()
                Navigation.findNavController(it).navigate(R.id.whistleblowerForm3Fragment)
            }
        }
    }
}
