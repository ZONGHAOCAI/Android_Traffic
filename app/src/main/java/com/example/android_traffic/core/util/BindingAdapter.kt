package com.example.android_traffic.core.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import java.io.ByteArrayOutputStream
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
@BindingAdapter("imgBase64")
fun ImageView.setImgBase64(base64: String?){
    base64?.let {
        val byteArray = Base64.getDecoder().decode(base64)
//        val byteArray = Base64.decode(base64, Base64.DEFAULT) //取得位元組陣列
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        setImageBitmap(bitmap) //目前是單向 純顯示但不可以編輯
    }
}

/**
 * 把imaview的圖轉成base64
 */
@RequiresApi(Build.VERSION_CODES.O)
@InverseBindingAdapter(attribute = "imgBase64")
fun ImageView.getImgBase64(): String? {
    drawable?.let {
        val stream = ByteArrayOutputStream()
        it.toBitmap().compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray = stream.toByteArray()
        return Base64.getEncoder().encodeToString(byteArray)
    }
    return null
}

//自訂的雙向綁定
@BindingAdapter("imgBase64AttrChanged")
fun ImageView.setOnImgBase64AttrChangedListener(listener: InverseBindingListener) {
    addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ -> listener.onChange() }
}

val SDF = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

@BindingAdapter("text")
fun TextView.setText(timestamp: Timestamp){
    text = SDF.format(timestamp)
}