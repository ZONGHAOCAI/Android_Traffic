package com.example.android_traffic.whistleblowerform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.login.Whistleblower

class WhistleblowerFormViewModel : ViewModel() {
    val whistleblower: MutableLiveData<Whistleblower> by lazy { MutableLiveData<Whistleblower>(Whistleblower()) }

    //若使用者沒有輸入,最底下會出現的顯示文字
    val whistleblowerResult: MutableLiveData<String> by lazy { MutableLiveData<String>() }


}