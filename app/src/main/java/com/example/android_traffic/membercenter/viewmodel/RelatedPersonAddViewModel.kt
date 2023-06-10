package com.example.android_traffic.membercenter.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.core.model.RelatedPerson
import com.example.android_traffic.core.service.Server
import com.example.android_traffic.core.service.requestTask
import com.google.gson.JsonObject

class RelatedPersonAddViewModel : ViewModel() {
    val relatedPerson: MutableLiveData<RelatedPerson> by lazy { MutableLiveData(RelatedPerson()) }







}