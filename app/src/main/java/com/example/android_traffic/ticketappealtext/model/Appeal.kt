package com.example.android_traffic.ticketappealtext.model

import java.io.Serializable
import java.sql.Timestamp

data class Appeal (
var ticketNo:String="", //罰單單號 b
var reason:String ="", // 申訴理由 a
var remark:String="",//h 申訴理由2 a

): Serializable