package com.example.android_traffic.membercenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RelatedPersonDataVehideDataViewModel : ViewModel() {
    val vehideType: MutableLiveData<String> by lazy { MutableLiveData() }
    val vehideData: MutableLiveData<String> by lazy { MutableLiveData() }
}