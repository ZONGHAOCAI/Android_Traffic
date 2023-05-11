package com.example.android_traffic.membercenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.R
import com.example.android_traffic.membercenter.model.RelatedPerson
import java.util.*

class RelatedPersonViewModel : ViewModel() {
    // 原始關係人列表
    private var relatedPersonList = mutableListOf<RelatedPerson>()
    // 受監控的LiveData，一旦指派新值就會更新關係人列表畫面
    val relatedPerson: MutableLiveData<List<RelatedPerson>> by lazy { MutableLiveData<List<RelatedPerson>>() }

    init {
        loadRelatedPerson()
    }


    /** 模擬取得遠端資料 */
    private fun loadRelatedPerson() {
        val relatedPersonList = mutableListOf<RelatedPerson>()
        relatedPersonList.add(RelatedPerson(R.drawable.ivy, "Ivy", "H123456789", Date(2022, 1, 3), "母女", "坦克車", "aaa-1234"))
        relatedPersonList.add(RelatedPerson(R.drawable.mary, "Mary", "A123456789", Date(2000, 12, 13), "母女2", "坦克車2", "bbb-1234"))
        relatedPersonList.add(RelatedPerson(R.drawable.sue, "Sue", "B123456789", Date(1999, 11, 23), "母女ˇ", "坦克車3", "bbb-1234"))
        this.relatedPersonList = relatedPersonList
        this.relatedPerson.value = this.relatedPersonList
    }
}