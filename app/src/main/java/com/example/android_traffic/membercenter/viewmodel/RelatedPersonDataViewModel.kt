package com.example.android_traffic.membercenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.core.model.RelatedPerson

/**
 * 單一關係人資料處理
 */
class RelatedPersonDataViewModel : ViewModel() {
    val relatedPerson: MutableLiveData<RelatedPerson> by lazy { MutableLiveData<RelatedPerson>() }
}