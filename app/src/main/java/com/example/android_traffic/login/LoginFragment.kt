package com.example.android_traffic.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.android_traffic.R
import com.example.android_traffic.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val viewModel: LoginViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {

            btnLoginLogin.setOnClickListener {
                viewModel?.run {
                    if (login.value!!.username.isEmpty()) {
                        loginResult.value = getString(R.string.errUsernameEmpty)
                        return@run
                    }
                    if (login.value!!.username.isNotEmpty()) {
                        login.value!!.username.matches(regex = Regex("[A-Z]\\d{9}"))
                    }

                    if (login.value!!.password.isEmpty()) {
                        edtTxtLoginPassword.error = getString(R.string.errPasswordEmpty)
                        return@run
                    } else {
                        Navigation.findNavController(it).navigate(R.id.mainFragment)
                    }
                }

            }
            btnLoginRegister.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.registerFragment)
            }

            tvLoginForgetPassword.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.forgetPasswordFragment)
            }

            tvLoginAdministrator.setOnClickListener {

                //TODO navigate destination location (wait for fragment to build)
                Navigation.findNavController(it).navigate(R.id.mainFragment)
            }

        }

    }

}