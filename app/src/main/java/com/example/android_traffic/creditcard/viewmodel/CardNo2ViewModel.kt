package com.example.android_traffic.creditcard.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.creditcard.model.Spot

class CardNo2ViewModel : ViewModel() {
val Card:MutableLiveData<Spot> by lazy { MutableLiveData<Spot>() }
}