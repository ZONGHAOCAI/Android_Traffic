package com.example.android_traffic.membercenter.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.android_traffic.MainViewModel
import com.example.android_traffic.membercenter.viewmodel.MemberCenterViewModel
import com.example.android_traffic.R
import com.example.android_traffic.databinding.FragmentMemberCenterBinding

class MemberCenterFragment : Fragment() {
    private lateinit var binding: FragmentMemberCenterBinding
//    private lateinit var viewModel: MemberCenterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel1: MemberCenterViewModel by viewModels()
        binding = FragmentMemberCenterBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel1
        binding.lifecycleOwner = this
        return binding.root
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(MemberCenterViewModel::class.java)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.title = getString(R.string.txt_MemberCenter_Title)
        with(binding) {
            //個人資料
            tvMeInfo.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.memberDataFragment)
            }
            //關係人資料
            tvRelatedPersonInfo.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.relatedPersonFragment)
            }
//            //檢查更新
//            tvUpdate.setOnClickListener {
//
//            }
//            //信用卡
//            tvCreditcard.setOnClickListener{
//
//            }
            //登出
            tvLogout.setOnClickListener {

            }
            //刪除帳號
//            tvDeleteAccount.setOnClickListener {
//                //刪除帳號
//            }


        }
    }
}