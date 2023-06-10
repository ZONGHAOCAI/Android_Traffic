package com.example.android_traffic.membercenter.controller

import android.os.Bundle
import android.text.InputFilter
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.android_traffic.R
import com.example.android_traffic.core.model.RelatedPerson
import com.example.android_traffic.core.service.Server
import com.example.android_traffic.core.service.requestTask
import com.example.android_traffic.databinding.FragmentRelatedPersonDataEditBinding
import com.example.android_traffic.membercenter.viewmodel.RelatedPersonDataEditViewModel
import com.google.gson.JsonObject

class RelatedPersonDataEditFragment : Fragment() {
    private lateinit var binding: FragmentRelatedPersonDataEditBinding

    private var editData: RelatedPerson = RelatedPerson()
    private var pass: Boolean = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: RelatedPersonDataEditViewModel by viewModels()
        binding = FragmentRelatedPersonDataEditBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            var title = ""
            arguments?.let { bundle ->
                bundle.getSerializable("type")?.let { type ->
                    bundle.getSerializable("relatedPersonData")?.let { data ->
                        val dataS = data as RelatedPerson
                        editData.id = dataS.id
                        when (type) {
                            "Name" -> {
                                title = getString(R.string.txt_MemberData_Edit_Name)
//                                relatedPersonData.name = dataS
                                editName(dataS)
                            }
                            "Relationship" -> {
                                title = "關係人"
//                                relatedPersonData.memberRelationship = dataS
                                editRelationship(dataS)
                            }
                            "IdentityNumber" -> {
                                title = getString(R.string.txt_MemberData_Edit_IdentityNumber)
//                                relatedPersonData.identityNumber = dataS
                                editIdentityNumber(dataS)
                            }
                        }
                        activity?.title = "修改$title" //設定標題
                    }
                }
            }

            btnRelatedPersonDataEdit.setOnClickListener {
                if (pass) {
                    val respBody = requestTask<JsonObject>(Server.urlFindRelatedperson, "PUT", editData)
                    respBody?.run {
                        if (get("successful").asBoolean) {
                            Navigation.findNavController(it).popBackStack()
                        }
                    }
                }
            }
        }
    }

    private fun editName(relatedPerson: RelatedPerson) {
        with(binding) {
            viewModel?.relatedPersonData?.value = relatedPerson.name
            viewModel?.relatedPersonData?.observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    etRelatedPersonDataEdit.error = getString(R.string.txt_MemberData_Edit_Error)
                    pass = false
                } else {
                    editData.name = viewModel?.relatedPersonData?.value.toString()
                    pass = true
                }
            }
        }
    }

    private fun editIdentityNumber(relatedPerson: RelatedPerson) {
        with(binding) {
            val inputFilter = InputFilter.LengthFilter(10)
            binding.etRelatedPersonDataEdit.filters = arrayOf(inputFilter)
            viewModel?.relatedPersonData?.value = relatedPerson.identityNumber
            viewModel?.relatedPersonData?.observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    etRelatedPersonDataEdit.error = getString(R.string.txt_MemberData_Edit_Error)
                    pass = false
                } else {
                    editData.identityNumber = viewModel?.relatedPersonData?.value.toString()
                    pass = true
                }
            }
        }
    }

    private fun editRelationship(relatedPerson: RelatedPerson) {
        with(binding) {
            viewModel?.relatedPersonData?.value = relatedPerson.memberRelationship
            viewModel?.relatedPersonData?.observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    etRelatedPersonDataEdit.error = getString(R.string.txt_MemberData_Edit_Error)
                    pass = false
                } else {
                    editData.memberRelationship = viewModel?.relatedPersonData?.value.toString()
                    pass = true
                }
            }
        }
    }


}