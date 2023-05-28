package com.example.android_traffic.whistleblowerform

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.android_traffic.databinding.FragmentWhistleblowerForm4Binding
import com.example.android_traffic.databinding.FragmentWhistleblowerFormBinding
import com.example.android_traffic.whistleblowerform.WhistleblowerForm4ViewModel

class WhistleblowerForm4Fragment : Fragment() {
    private lateinit var binding: FragmentWhistleblowerForm4Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWhistleblowerForm4Binding.inflate(inflater, container, false)
        val viewModel : WhistleblowerForm4ViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

}