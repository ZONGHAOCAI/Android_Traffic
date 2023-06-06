package com.example.android_traffic.whistleblowerform.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.whistleblowerform.model.Whistleblower

class WhistleblowerForm2ViewModel : ViewModel() {
    val whistleblower: MutableLiveData<Whistleblower> by lazy { MutableLiveData<Whistleblower>() }

    fun addWhistleblower() {

    }
}