package com.example.android_traffic.whistleblowerform.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.whistleblowerform.model.Whistleblower

class WhistleblowerFormViewModel : ViewModel() {
    val whistleblower: MutableLiveData<Whistleblower> by lazy { MutableLiveData<Whistleblower>(
        Whistleblower(violationsDate = "2023-06- 08", violationsTime = "17:26", violationsAddress = "台北市中山區中正路1號",
            violations = "闖紅燈", remark = "交叉路口", vehicleType = "汽車", vehicleNo = "ABC-1234"
                 )
    ) }
    //若使用者沒有輸入,最底下會出現的顯示文字
    val whistleblowerResult: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    val textDate: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val textTime: MutableLiveData<String> by lazy { MutableLiveData<String>() }


}