package com.example.android_traffic.whistleblowerform.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.android_traffic.R
import com.example.android_traffic.databinding.FragmentWhistleblowerFormBinding
import com.example.android_traffic.whistleblowerform.viewModel.WhistleblowerFormViewModel

class WhistleblowerFormFragment : Fragment() {
    private lateinit var binding: FragmentWhistleblowerFormBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWhistleblowerFormBinding.inflate(inflater, container, false)
        val viewModel: WhistleblowerFormViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            btnWhistleblowerNext.setOnClickListener {
                viewModel?.run {
                    var whistleblowerFormAreFilled = true
                    //違規時間
                    if (whistleblower.value?.violationTime.isNullOrEmpty()) {
                        edtTxtViolationTimeWhistleblower.error = "請輸入違規時間"
                        whistleblowerFormAreFilled = false
                    }
                    //違規車號
                    if (whistleblower.value?.violationCar.isNullOrEmpty()) {
                        edtTxtViolationCarWhistleblower.error = "請輸入違規車號"
                        whistleblowerFormAreFilled = false
                    }
                    //違規地點
                    if (whistleblower.value?.violationLocation.isNullOrEmpty()) {
                        edtTxtViolationLocationWhistleblower.error = "請輸入違規地點"
                        whistleblowerFormAreFilled = false
                    }
                    //交叉入口
                    if (whistleblower.value?.violationIntersection.isNullOrEmpty()) {
                        edtTxtIntersectionWhistleblower.error = "請輸入交叉入口"
                        whistleblowerFormAreFilled = false
                    }
                    //地點備註
                    if (whistleblower.value?.violationLocationNote.isNullOrEmpty()) {
                        edtTxtLocationNoteWhistleblower.error = "請輸入地點備註"
                        whistleblowerFormAreFilled = false
                    }
                    //違規事實
                    if (whistleblower.value?.violationFact.isNullOrEmpty()) {
                        edtTxtViolationFactWhistleblower.error = "請輸入違規事實"
                        whistleblowerFormAreFilled = false
                    }
                    //違規事實說明
                    if (whistleblower.value?.violationFactDetails.isNullOrEmpty()) {
                        edtTxtViolationFactDetailsWhistleblower.error = "請輸入違規事實說明"
                        whistleblowerFormAreFilled = false
                    }
                    if(whistleblowerFormAreFilled){
                        //TODO 剪影片
                        val bundle = Bundle()
                        val whistleblower = viewModel?.whistleblower?.value
                        bundle.putSerializable("whistleblower", whistleblower)
                        Navigation.findNavController(it)
                            .navigate(R.id.whistleblowerForm2Fragment, bundle)
                        println(bundle)
                    }
                }

            }

        }

    }
}