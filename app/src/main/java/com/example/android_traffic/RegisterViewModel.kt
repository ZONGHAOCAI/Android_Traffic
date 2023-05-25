package com.example.android_traffic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_traffic.modelLogin.Login

class RegisterViewModel : ViewModel() {
    val login: MutableLiveData<Login> by lazy { MutableLiveData<Login>() }
    val name: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val birthday: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val nickname: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val address: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val email: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val result: MutableLiveData<String> by lazy { MutableLiveData<String>() }


//    fun register() {
//        login.value?.run {
//            //姓名
//            if (name.value!!.isEmpty()) {
//                result.value = "姓名不可以為空白,請用真實姓名"
//            }
//            //帳號
//            if (username.matches(regex = Regex("[A-Z]\\d{9}"))) {
//                result.value = "使⽤者名稱:須為身分證字號,且第一個英文字母為大寫"
//                return
//            }
//            //密碼
//            if (password.length < 6 || password.length > 12) {
//                result.value = "密碼:⻑度6~12"
//                return
//            }
//            //確認密碼
//            if (password != confirmPassword) {
//                result.value = "密碼與確認密碼不符合"
//                return
//            }
//            //出生年月日
//            if (birthday.value!!.isEmpty()) {
//                result.value = "生日不可以為空白"
//                return
//            }else{birthday.value!!.matches(Regex("^\\d{6}\$|^\\d{7}\$"))}
//
//            //暱稱
//            if (nickname.value!!.isEmpty() || nickname.value!!.length > 20) {
//                result.value = "暱稱:⻑度1~20"
//                return
//            }
//
//            //地址
//            if (address.value!!.isEmpty()) {
//                result.value = "地址不可以為空白"
//                return
//            }
//
//            //Email
//            if (email.value!!.isEmpty()) {
//                result.value = "email不可以為空白"
//                return
//            } else {
//                email.value!!.matches(Regex("[A-Za-z0-9._%+-]+@[A-Za-z\\d.-]+\\.[A-Za-z]{2,}"))
//            }
//
//            //手機
//            if (phone.isEmpty()) {
//                result.value = "手機不可以為空白"
//                return
//            } else {
//                phone.matches(Regex("^\\d{10}$"))
//            }
//
//            //驗證碼
//            if (code.isEmpty()) {
//                result.value = "請先發送簡訊取得驗證碼"
//                return
//            }
//        }
//
//
//    }
}
