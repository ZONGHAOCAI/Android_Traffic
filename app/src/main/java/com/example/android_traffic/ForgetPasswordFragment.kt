package com.example.android_traffic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.android_traffic.databinding.FragmentForgetPasswordBinding

class ForgetPasswordFragment : Fragment() {
    private lateinit var binding: FragmentForgetPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForgetPasswordBinding.inflate(inflater, container, false)
        val viewModel: ForgetPasswordViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            //按下一步,檢查值,挑轉至重設密碼
            btnNext.setOnClickListener {
                viewModel?.run {
                    //帳號
                    if (login.value!!.username.isEmpty()) {
                        edtTxtForgetUsername.error = getString(R.string.errUsernameEmpty)
                        return@run
                    }
                    if (login.value!!.username.isNotEmpty()) {
                        login.value!!.username.matches(regex = Regex("[A-Z]\\d{9}"))
                    }

                    //手機
                    if (login.value!!.phone.isEmpty()) {
                        edtTxtForgetPhone.error = "手機不可以為空白"
                        return@run
                    } else {
                        login.value!!.phone.matches(Regex("^\\d{10}$"))
                    }

                    //驗證碼
                    if (login.value!!.code.isEmpty()) {
                        edtTxtForgetConfirmNumber.error = "請先發送簡訊取得驗證碼"
                        return@run
                    }

                    //TODO 送出簡訊按鈕
                    //TODO 送出驗證碼按鈕
                    //TODO 下一步
                    Navigation.findNavController(it).navigate(R.id.edTxtResetPassword)
                }

            }


        }


    }

}