package com.example.android_traffic.whistleblowerform

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.android_traffic.R
import com.example.android_traffic.databinding.FragmentWhistleblowerFormBinding
import com.example.android_traffic.login.Whistleblower

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

                    //違規時間
                    if (whistleblower.value!!.violationTime.isEmpty()) {
                        whistleblowerResult.value = "請輸入違規時間"
                    }
                    //違規車號
                    if (whistleblower.value!!.violationCar.isEmpty()) {
                        whistleblowerResult.value = "請輸入違規車號"
                    }
                    //違規地點
                    if (whistleblower.value!!.violationLocation.isEmpty()) {
                        whistleblowerResult.value = "請輸入違規地點"
                    }
                    //交叉入口
                    if (whistleblower.value!!.violationIntersection.isEmpty()) {
                        whistleblowerResult.value = "請輸入交叉入口"
                    }
                    //地點備註
                    if (whistleblower.value!!.violationLocationNote.isEmpty()) {
                        whistleblowerResult.value = "請輸入地點備註"
                    }
                    //違規事實
                    if (whistleblower.value!!.violationFact.isEmpty()) {
                        whistleblowerResult.value = "請輸入違規事實"
                    }
                    //違規事實說明
                    if (whistleblower.value!!.violationFactDetails.isEmpty()) {
                        whistleblowerResult.value = "請輸入違規事實說明"
                    }
                    //TODO 影片

                }

                val bundle = Bundle()
                val whistleblower = Whistleblower(
                    violationTime = "",
                    violationCar = "",
                    violationLocation = "",
                    violationIntersection = "",
                    violationLocationNote = "",
                    violationFact = "",
                    violationFactDetails = ""
                )
                Navigation.findNavController(it).navigate(R.id.whistleblowerForm2Fragment)
            }
        }

    }
}