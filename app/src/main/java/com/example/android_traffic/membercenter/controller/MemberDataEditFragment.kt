package com.example.android_traffic.membercenter.controller

import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.android_traffic.R
import com.example.android_traffic.databinding.FragmentMemberDataEditBinding
import com.example.android_traffic.core.model.Member
import com.example.android_traffic.core.service.Server
import com.example.android_traffic.core.service.requestTask
import com.example.android_traffic.membercenter.viewmodel.MemberDataEditViewModel
import com.google.gson.JsonObject


class MemberDataEditFragment : Fragment() {
    private lateinit var binding: FragmentMemberDataEditBinding

    private var memberData: Member = Member()
    private var editData: Member = Member()
    private var onePass: Boolean = true
    private var twoPass: Boolean = true
    private var threePass: Boolean = true
    private var passError: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: MemberDataEditViewModel by viewModels()
        binding = FragmentMemberDataEditBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            arguments?.let { bundle ->
                bundle.getSerializable("memberDataTitle")?.let { title ->
                    bundle.getSerializable("memberData")?.let { data ->
                        val tempTitle = title.toString().toInt()
                        memberData = data as Member
                        activity?.title = getString(tempTitle) //設定標題
                        when (tempTitle) { //判斷修改項目是什麼
                            R.string.txt_MemberData_Edit_Avatar -> {}//待用
                            R.string.txt_MemberData_Edit_Name -> editName(memberData.name)
                            R.string.txt_MemberData_Edit_Nickname -> editNickname(memberData.nickname)
                            R.string.txt_MemberData_Edit_IdentityNumber -> editIdentityNumber(
                                memberData.identityNumber
                            )
                            R.string.txt_MemberData_Edit_Birthday -> editBirthday(memberData.birthday)
                            R.string.txt_MemberData_Edit_PhoneNo -> editPhoneNo(memberData.phoneNo)
                            R.string.txt_MemberData_Edit_Email -> editEmail(memberData.email)
                            R.string.txt_MemberData_Address -> editAddress(memberData.address)
                            R.string.txt_MemberData_Edit_Password -> editPassword(memberData.password)
                            else -> {}
                        }
                    }
                }
            }
            btMemberDataEdit.setOnClickListener {
                if (passError == true) {
                    etMemberDataEditOne.error = "密碼錯誤"
                    return@setOnClickListener
                }
                if (onePass && twoPass && threePass) {
                    val respBody = requestTask<JsonObject>(Server.url, "PUT", editData)
                    respBody?.run {
                        if (get("successful").asBoolean) {
                            Navigation.findNavController(it).popBackStack()
                        }
                    }
                }
            }
        }
    }

    /**
     * 限制第一個輸入框的最大字數
     */
    private fun maxLen(max: Int) {
        val inputFilter = InputFilter.LengthFilter(max)
        binding.etMemberDataEditOne.filters = arrayOf(inputFilter)
    }

    //判斷商業邏輯
    private fun editName(name: String) {
        with(binding) {
            maxLen(100)
            viewModel?.memberData?.value = name
            viewModel?.memberData?.observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    etMemberDataEditOne.error = getString(R.string.txt_MemberData_Edit_Error)
                    onePass = false
                } else {
                    editData.name = viewModel?.memberData?.value.toString()
                    onePass = true
                }
            }
        }
    }

    private fun editNickname(nickname: String) {
        with(binding) {
            maxLen(100)
            viewModel?.memberData?.value = nickname
            viewModel?.memberData?.observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    etMemberDataEditOne.error = getString(R.string.txt_MemberData_Edit_Error)
                    onePass = false
                } else {
                    editData.nickname = viewModel?.memberData?.value.toString()
                    onePass = true
                }
            }
        }
    }

    private fun editIdentityNumber(identityNumber: String) {
        with(binding) {
            maxLen(10)
            viewModel?.memberData?.value = identityNumber
            viewModel?.memberData?.observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    etMemberDataEditOne.error = getString(R.string.txt_MemberData_Edit_Error)
                    onePass = false
                } else {
                    editData.identityNumber = viewModel?.memberData?.value.toString()
                    onePass = true
                }
            }
        }
    }

    private fun editBirthday(birthday: String) {
        with(binding) {
            maxLen(9)
            etMemberDataEditOne.inputType = InputType.TYPE_CLASS_DATETIME
            viewModel?.memberData?.value = birthday
            viewModel?.memberData?.observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    etMemberDataEditOne.error = getString(R.string.txt_MemberData_Edit_Error)
                    onePass = false
                } else {
                    editData.birthday = viewModel?.memberData?.value.toString()
                    onePass = true
                }
            }
        }
    }

    private fun editPhoneNo(phoneNo: String) {
        with(binding) {
            maxLen(10)
            etMemberDataEditOne.inputType = InputType.TYPE_CLASS_PHONE
            viewModel?.memberData?.value = phoneNo
            viewModel?.memberData?.observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    etMemberDataEditOne.error = getString(R.string.txt_MemberData_Edit_Error)
                    onePass = false
                } else {
                    editData.phoneNo = viewModel?.memberData?.value.toString()
                    onePass = true
                }
            }
        }
    }

    private fun editEmail(email: String) {
        with(binding) {
            maxLen(100)
            etMemberDataEditOne.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            viewModel?.memberData?.value = email
            viewModel?.memberData?.observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    etMemberDataEditOne.error = getString(R.string.txt_MemberData_Edit_Error)
                    onePass = false
                } else {
                    editData.email = viewModel?.memberData?.value.toString()
                    onePass = true
                }
            }
        }
    }

    private fun editAddress(address: String) {
        with(binding) {
            maxLen(100)
            viewModel?.memberData?.value = address
            viewModel?.memberData?.observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    etMemberDataEditOne.error = getString(R.string.txt_MemberData_Edit_Error)
                    onePass = false
                } else {
                    editData.address = viewModel?.memberData?.value.toString()
                    onePass = true
                }
            }
        }
    }

    private fun editPassword(password: String) {
        with(binding) {
            maxLen(100)
            etMemberDataEditOne.hint =
                resources.getString(R.string.txt_MemberData_Edit_OldPassword)
            etMemberDataEditOne.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD //輸入框改成密碼模式
            etMemberDataEditTwo.visibility = View.VISIBLE
            etMemberDataEditThree.visibility = View.VISIBLE
            //監控舊密碼
            viewModel?.memberData?.observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    etMemberDataEditOne.error = getString(R.string.txt_MemberData_Edit_Error)
                    onePass = false
                } else if (password != etMemberDataEditOne.text.toString()){
                    passError = true
                } else {
                    onePass = true
                    passError = false
                }
            }
            //監控新密碼
            viewModel?.pass?.observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    etMemberDataEditTwo.error = getString(R.string.txt_MemberData_Edit_Error)
                    twoPass = false
                } else {
                    editData.password = etMemberDataEditTwo.text.toString()
                    twoPass = true
                }
            }
            //監控確認新密碼
            viewModel?.cPass?.observe(viewLifecycleOwner) {
                if (viewModel?.pass?.value != viewModel?.cPass?.value) {
                    etMemberDataEditThree.error =
                        getString(R.string.txt_MemberData_Edit_passwordError)
                    threePass = false
                } else threePass = true
            }
        }
    }
}
