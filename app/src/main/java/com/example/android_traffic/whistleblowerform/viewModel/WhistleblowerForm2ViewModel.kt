package com.example.android_traffic.whistleblowerform.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.core.service.requestTask
import com.example.android_traffic.whistleblowerform.model.Whistleblower
import com.google.gson.JsonObject

class WhistleblowerForm2ViewModel : ViewModel() {
    val whistleblower: MutableLiveData<Whistleblower> by lazy { MutableLiveData<Whistleblower>() }

    val textDate: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val textTime: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    fun addWhistleblower() {
        whistleblower.value?.memId = "1"
        println(whistleblower.value)
        requestTask<JsonObject>("http://10.0.2.2:8080/javaweb-Traffic/whistleblower/", "POST", whistleblower.value)
    }
}