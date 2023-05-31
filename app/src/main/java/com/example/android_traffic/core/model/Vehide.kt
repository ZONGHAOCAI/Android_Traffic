package com.example.android_traffic.core.model

import java.io.Serializable
import java.util.Date

/**
 * 車輛資料表用
 * @param 車種
 * @param 車號
 */
class Vehide(var vehideType: String, var vehideNumber: String) :
    Serializable {
}