package com.example.android_traffic.login.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.android_traffic.R
import com.example.android_traffic.login.viewModel.ResetPasswordViewModel
import com.example.android_traffic.databinding.FragmentResetPasswordBinding

class ResetPasswordFragment : Fragment() {
    private lateinit var binding: FragmentResetPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().title = getString(R.string.txtResetPassword)
        binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        val viewModel : ResetPasswordViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            arguments.let {
                if (it != null) {
                    viewModel?.member?.value?.phoneNo = it.getString("mobilePhone").toString()
                    println(it.getString("mobilePhone").toString())
                }
            }
            btResetPasswordSend.setOnClickListener {
                viewModel?.run {
                    if (member.value!!.password.length < 6 || member.value!!.password.length > 12){
                        edTxtResetPassword.error ="密碼:⻑度6~12"
                        return@run
                    }

                    if (member.value!!.password != confirmPassword.value) {
                        edTxtResetPasswordConfirm.error = "密碼與確認密碼不符合"
                        return@run
                    }
                    viewModel?.resetPassword()
                    Navigation.findNavController(it).navigate(R.id.loginFragment)
                    Toast.makeText(requireContext(), "密碼修改成功,請使用新密碼登入", Toast.LENGTH_LONG).show()
                }

            }
        }

    }
}