package com.example.android_traffic

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.android_traffic.databinding.FragmentRegisterBinding
import kotlin.math.log

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val viewModel: RegisterViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            btRegisterSend.setOnClickListener {
                viewModel.run {
                    if (login.value!!.username.matches(regex = Regex("[A-Z]\\d{9}"))) {
                        result.value = "使⽤者名稱:須為身分證字號,且第一個英文字母為大寫"
                        return@run
                    }

                    if (login.value!!.password.length < 6 || login.value!!.password.length > 12) {
                        result.value = "密碼:⻑度6~12"
                        return@run
                    }

                    if (login.value!!.password != login.value!!.confirmPassword) {
                        result.value = "密碼與確認密碼不符合"
                        return@run
                    }
                    //出生年月日
                    if (birthday.value!!.isEmpty()) {
                        result.value = "生日不可以為空白"
                        return@run
                    }else{birthday.value!!.matches(Regex("^\\d{6}\$|^\\d{7}\$"))}

                    //暱稱
                    if (nickname.value!!.isEmpty() || nickname.value!!.length > 20) {
                        result.value = "暱稱:⻑度1~20"
                        return@run
                    }

                    //地址
                    if (address.value!!.isEmpty()) {
                        result.value = "地址不可以為空白"
                        return@run
                    }

                    //Email
                    if (email.value!!.isEmpty()) {
                        result.value = "email不可以為空白"
                        return@run
                    } else {
                        email.value!!.matches(Regex("[A-Za-z0-9._%+-]+@[A-Za-z\\d.-]+\\.[A-Za-z]{2,}"))
                    }

                    //手機
                    if (login.value!!.phone.isEmpty()) {
                        result.value = "手機不可以為空白"
                        return@run
                    } else {
                        login.value!!.phone.matches(Regex("^\\d{10}$"))
                    }

                    //驗證碼
                    if (login.value!!.code.isEmpty()) {
                        result.value = "請先發送簡訊取得驗證碼"
                        return@run
                    }
                    Navigation.findNavController(it).navigate(R.id.loginFragment)
                }
            }


        }
    }
}