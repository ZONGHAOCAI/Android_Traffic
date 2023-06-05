package com.example.android_traffic.membercenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.core.model.Vehide
import com.example.android_traffic.core.service.Server.Companion.urlVehide
import com.example.android_traffic.core.service.requestTask
import com.google.gson.reflect.TypeToken

class MemberDataVehideDataViewModel : ViewModel() {
    val vehideType: MutableLiveData<String> by lazy { MutableLiveData() }
    val vehideData: MutableLiveData<String> by lazy { MutableLiveData() }
    val vehideDataList: MutableLiveData<List<Vehide>> by lazy { MutableLiveData() }

    fun init() {
        val type = object : TypeToken<List<Vehide>>() {}.type
        vehideDataList.value = requestTask<List<Vehide>>("$urlVehide/1", respBodyType = type)

    }



}


