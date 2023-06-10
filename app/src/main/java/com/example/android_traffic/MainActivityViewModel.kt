package com.example.android_traffic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private val _resultLiveData: MutableLiveData<Boolean> by lazy { MutableLiveData(false) }
    val resultLiveData: LiveData<Boolean> = _resultLiveData

    fun setResult(result: Boolean) {
        _resultLiveData.value = result
    }
}