package com.example.android_traffic

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.android_traffic.databinding.FragmentResetPasswordBinding

class ResetPasswordFragment : Fragment() {
    private lateinit var binding: FragmentResetPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        val viewModel : ResetPasswordViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            btResetPasswordSend.setOnClickListener {
                viewModel?.run {
                    if (login.value!!.password.length < 6 || login.value!!.password.length > 12){
                        edTxtResetPassword.error ="密碼:⻑度6~12"
                        return@run
                    }

                    if (login.value!!.password != login.value!!.confirmPassword) {
                        edTxtResetPasswordConfirm.error = "密碼與確認密碼不符合"
                        return@run
                    }
                    Navigation.findNavController(it).navigate(R.id.loginFragment)
                }

            }
        }

    }
}