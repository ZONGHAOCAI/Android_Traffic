package com.example.android_traffic.creditcard.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.android_traffic.R
import com.example.android_traffic.creditcard.viewmodel.CardNo2ViewModel
import com.example.android_traffic.databinding.FragmentCardNo2Binding

class CardNo2Fragment : Fragment() {


    private lateinit var binding: FragmentCardNo2Binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: CardNo2ViewModel by viewModels()
        binding = FragmentCardNo2Binding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(binding) {
            add.setOnClickListener {
                val uid = etuid.text.toString().trim()
                val data = etdata.text.toString().trim()
                val safe = etsafe.text.toString().trim()

                val happy = "${getString(R.string.uid)}:$uid\n" +
                        "${getString(R.string.etdata)}:$data\n" +
                        "${getString(R.string.etsafe)}:$safe"
                        if (uid.isEmpty() || data.isEmpty() || safe.isEmpty()) {
                            viewModel?.Card?.value?.text = getString(R.string.errover)
                            return@setOnClickListener
                        } else {
                            viewModel?.Card?.value?.text = happy
                        }
                if (!inputValid()){
                    return@setOnClickListener
                }
                val bundle=Bundle()

            }
        }
    }

    private fun inputValid(): Boolean {
        var valid = true
        with(binding) {
            val uid = viewModel?.Card?.value?.uid?.trim()
            val data = viewModel?.Card?.value?.data?.trim()
            val safe = viewModel?.Card?.value?.save?.trim()
            if (uid.isNullOrEmpty()||uid.length != 16 || !uid.all { it.isDigit() }) {
                etuid.error = getString(R.string.erruid)
                valid = false
            }
            if (data != null && !data.matches(Regex("""^\d{2}/\d{2}$"""))){
                etdata.error = getString(R.string.errdate)
                valid = false
            }

            if (safe.isNullOrEmpty()||safe.length != 3 || !safe.all { it.isDigit() }) {
                etsafe.error = getString(R.string.errsafe)
                valid = false
            }
            return valid
        }
    }
}