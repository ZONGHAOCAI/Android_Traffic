package com.example.android_traffic.membercenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RelatedPersonDataEditViewModel : ViewModel() {
    val relatedPersonData: MutableLiveData<String> by lazy { MutableLiveData("") }
}