package com.example.android_traffic.whistleblowerform.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.android_traffic.R
import com.example.android_traffic.databinding.FragmentWhistleblowerForm3Binding
import com.example.android_traffic.whistleblowerform.viewModel.WhistleblowerForm3ViewModel

class WhistleblowerForm3Fragment : Fragment() {
    private lateinit var binding: FragmentWhistleblowerForm3Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWhistleblowerForm3Binding.inflate(inflater, container, false)
        val viewModel : WhistleblowerForm3ViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            //todo 顯示案件編號 後端資料庫自動編號？
            //繼續檢舉
            btnNextCase.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.whistleblowerFormFragment)
            }

            //回首頁
            btnWhistleblower3BacktoMainPage.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.mainFragment)
            }
        }
    }
}