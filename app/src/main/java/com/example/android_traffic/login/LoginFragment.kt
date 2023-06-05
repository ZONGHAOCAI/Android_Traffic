package com.example.android_traffic.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.android_traffic.R
import com.example.android_traffic.core.model.Member
import com.example.android_traffic.core.service.Server
import com.example.android_traffic.core.service.requestTask
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
            viewModel?.init()
            btnLoginLogin.setOnClickListener {
                viewModel?.run {
                    if (member.value!!.phoneNo.isEmpty()) {
                        edtTxtLoginUsername.error= getString(R.string.errUsernameEmpty)
                        return@run
                    }
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