package com.example.android_traffic.whistleblowerform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.login.Whistleblower

class WhistleblowerFormViewModel : ViewModel() {
    val whistleblower: MutableLiveData<Whistleblower> by lazy { MutableLiveData<Whistleblower>() }
    val whistleblowerResult: MutableLiveData<String> by lazy { MutableLiveData<String>() }


}