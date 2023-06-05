package com.example.android_traffic.membercenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.R
import com.example.android_traffic.core.model.RelatedPerson

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
        relatedPersonList.add(RelatedPerson(R.drawable.ivy, "海豹", "H123456789", "109-01-02", "老爸"))
        relatedPersonList.add(RelatedPerson(R.drawable.mary, "狗狗", "A123456789","107-02-03", "老媽"))
        relatedPersonList.add(RelatedPerson(R.drawable.sue, "兔兔", "B123456789", "101-05-04", "不認識的人"))
        this.relatedPersonList = relatedPersonList
        this.relatedPerson.value = this.relatedPersonList
    }
}