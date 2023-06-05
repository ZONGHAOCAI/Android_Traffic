package com.example.android_traffic.login

import java.io.Serializable

data class Whistleblower(
    var violationTime: String? = null,
    var violationCar: String? = null,
    var violationLocation: String? = null,
    var violationIntersection: String? = null,
    var violationLocationNote: String? = null,
    var violationFact: String? = null,
    var violationFactDetails: String? = null,
): Serializable
