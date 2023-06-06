package com.example.android_traffic.login.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android_traffic.databinding.FragmentRegisterBinding
import com.example.android_traffic.login.viewModel.RegisterViewModel

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
                viewModel?.run {

                    //姓名
                    if (name.value!!.isEmpty()) {
                        edtTxtName.error = "姓名不可以空白"
                        return@run
                    }

                    //身分證字號
                    if (login.value!!.username.isEmpty()) {
                        edtTxtId.error = "身分證字號不可以空白"
                    }

                    //密碼
                    if (login.value!!.password.isEmpty()) {
                        edtTxtPassword.error = "密碼不可以空白"
                    }

                    //確認密碼
                    if (login.value?.password != login.value?.confirmPassword){
                        edtTxtRegisterConfirmCode.error = "密碼和確認密碼需相同"
                    }

                    //出生年月日
                    if (birthday.value!!.isEmpty()) {
                        edtTxtBirthday.error = "出生年月日不可以空白"
                    }

                    //暱稱
                    if (nickname.value!!.isEmpty()) {
                        edtTxtNickname.error = "暱稱不可以空白"
                    }

                    //地址
                    if (address.value!!.isEmpty()) {
                        edtTxtRegisterAddress.error = "地址不可以空白"
                    }

                    //email
                    if (email.value!!.isEmpty()) {
                        edtTxtRegisterEmail.error = "email不可以空白"
                    }

                    //手機
                    if (login.value?.phone!!.isEmpty()) {
                        edtTxtRegisterPhone.error = "手機不可以空白"
                    }

                    //驗證碼
                    if (login.value?.code!!.isEmpty()) {
                        edtTxtRegisterConfirmCode.error = "驗證碼不可以空白"
                    }

                }
            }
        }
    }


}