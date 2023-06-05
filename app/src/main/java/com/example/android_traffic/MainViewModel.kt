package com.example.android_traffic

import androidx.lifecycle.ViewModel
import com.example.android_traffic.core.service.Server.Companion.url
import com.example.android_traffic.core.service.requestTask

class MainViewModel : ViewModel() {
var memberId: Int? = 0

    init {
        memberId = requestTask(url, "OPTIONS")
    }
}