package com.example.android_traffic.whistleblowerform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.core.service.requestTask
import com.example.android_traffic.login.Whistleblower

class WhistleblowerForm2ViewModel : ViewModel() {
    val whistleblower: MutableLiveData<Whistleblower> by lazy { MutableLiveData<Whistleblower>() }

    fun addWhistleblower() {

    }
}