package com.example.android_traffic.membercenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.core.model.RelatedPerson
import com.example.android_traffic.core.service.Server.Companion.urlFindRelatedperson
import com.example.android_traffic.core.service.requestTask
import com.google.gson.reflect.TypeToken

class RelatedPersonViewModel : ViewModel() {
    // 原始關係人列表
    var relatedPersonList = mutableListOf<RelatedPerson>()

    // 受監控的LiveData，一旦指派新值就會更新關係人列表畫面
    val relatedPerson: MutableLiveData<List<RelatedPerson>> by lazy { MutableLiveData<List<RelatedPerson>>() }
    val relatedPersonN: MutableLiveData<RelatedPerson> by lazy { MutableLiveData(RelatedPerson()) }
    init {
        loadRelatedPerson()
    }


    /** 模擬取得遠端資料 */
    private fun loadRelatedPerson() {
        val type = object : TypeToken<List<RelatedPerson>>() {}.type
        relatedPerson.value = requestTask<List<RelatedPerson>>(urlFindRelatedperson, respBodyType = type)
//        relatedPersonList = (this.relatedPerson.value?.toMutableList() ?: null) as MutableList<RelatedPerson>
        relatedPersonList = relatedPerson.value?.toMutableList() ?: mutableListOf()


    }

    fun change(value: RelatedPerson?) {
//        relatedPersonN.value?.let {
//            relatedPersonList.add(it) }
        var relatedPersonListQ = relatedPerson.value?.toMutableList() // 獲取可變的 List

        if (value != null) {
            println("=====================r")
            relatedPersonListQ!!.add(value)
        } // 添加物件到 List

        relatedPerson.value = relatedPersonListQ
        if (relatedPersonListQ != null) {
            relatedPersonList = relatedPersonListQ
        }
//        relatedPerson.value = relatedPersonList
    }
}