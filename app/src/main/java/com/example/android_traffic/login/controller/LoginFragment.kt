package com.example.android_traffic.login.controller

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.android_traffic.R
import com.example.android_traffic.databinding.FragmentLoginBinding
import com.example.android_traffic.login.viewModel.LoginViewModel


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().title = getString(R.string.txtLogin)
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val viewModel: LoginViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            viewModel?.init()
            btnLoginLogin.setOnClickListener {
                viewModel?.run {
                    if (member.value!!.phoneNo.isEmpty()) {
                        edtTxtLoginUsername.error= getString(R.string.errUsernameEmpty)
                        return@run
                    }

                    //TODO 登入改用手機號碼 (Regex)
//                    if ( !login.value?.phone?.matches(regex = Regex("[A-Z]\\d{9}"))!!) {
//                        loginResult.value = "帳號為手機號碼"
//                        return@run
//                    }

                    if (member.value!!.password.isEmpty()) {
                        edtTxtLoginPassword.error = getString(R.string.errPasswordEmpty)
                        return@run
                    }
                    if (login()){
                        Navigation.findNavController(it).navigate(R.id.mainFragment)
                    }
                    //move to VM?
//                    val respBody = requestTask<Member>("${Server.url}/${member.value!!.phoneNo}/${member.value!!.password}")
//                    if (respBody?.id != null){
//                        Navigation.findNavController(it).navigate(R.id.mainFragment)
//                    }

                }

            }

            //註冊按鈕
            btnLoginRegister.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.registerFragment)
            }
            //忘記密碼按鈕
            tvLoginForgetPassword.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.newForgetPasswordFragment)
            }

            tvLoginAdministrator.setOnClickListener {
                showAlertDialog()

                //TODO navigate destination location (wait for fragment to build)
//                Navigation.findNavController(it).navigate(R.id.mainFragment)
            }

        }

    }


    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("哈囉")
            .setMessage("管理員服務正在籌備中,我們會盡快為您服務!")
            .setPositiveButton("確定") { dialog, id ->
                // 點擊確定按鈕後的處理邏輯
            }
            .setNegativeButton("取消") { dialog, id ->
                // 點擊取消按鈕後的處理邏輯
            }
            .show()
    }

}