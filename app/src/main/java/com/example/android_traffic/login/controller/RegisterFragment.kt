package com.example.android_traffic.login.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.android_traffic.R
import com.example.android_traffic.core.service.Server.Companion.url
import com.example.android_traffic.core.service.requestTask
import com.example.android_traffic.databinding.FragmentRegisterBinding
import com.example.android_traffic.login.viewModel.RegisterViewModel
import com.google.gson.JsonObject

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().title = getString(R.string.txtRegister)
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
                    if (member.value!!.name.isEmpty()) {    //改member
                        edtTxtName.error = "姓名不可以空白"
                        return@setOnClickListener
                    }

                    //身分證字號
                    if (member.value!!.identityNumber.isEmpty()) {   //改member
                        edtTxtId.error = "身分證字號不可以空白"
                    }

                    //密碼
                    if (member.value!!.password.isEmpty()) {    //改member
                        edtTxtPassword.error = "密碼不可以空白"
                    }

                    //確認密碼
                    if (member.value?.password != login.value?.confirmPassword) {     //改member   //確認密碼照舊
                        edtTxtRegisterConfirmCode.error = "密碼和確認密碼需相同"
                    }

                    //出生年月日
                    if (member.value?.birthday!!.isEmpty()) {    //改member
                        edtTxtBirthday.error = "出生年月日不可以空白"
                    }

                    //暱稱
                    if (member.value?.nickname!!.isEmpty()) {     //改member
                        edtTxtNickname.error = "暱稱不可以空白"
                    }

                    //地址
                    if (member.value?.address!!.isEmpty()) {   //改member
                        edtTxtRegisterAddress.error = "地址不可以空白"
                    }

                    //email
                    if (member.value?.email!!.isEmpty()) {     //改member
                        edtTxtRegisterEmail.error = "email不可以空白"
                    }

                    //手機
                    if (member.value?.phoneNo!!.isEmpty()) {    //改member
                        edtTxtRegisterPhone.error = "手機不可以空白"
                    }

                    //驗證碼
                    if (login.value?.code!!.isEmpty()) {
                        edtTxtRegisterConfirmCode.error = "驗證碼不可以空白"
                    }

                    val respBody = requestTask<JsonObject>(url, "POST", member?.value) //連到DB註冊

                    if (respBody?.get("successful")!!.asBoolean) { //註冊成功回傳
                        Navigation.findNavController(view).popBackStack() //清除上一頁
                        Navigation.findNavController(view).navigate(R.id.loginFragment)
                        Toast.makeText(requireContext(), "註冊成功！", Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }


    }
}