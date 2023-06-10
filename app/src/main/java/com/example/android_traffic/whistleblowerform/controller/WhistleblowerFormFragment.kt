package com.example.android_traffic.whistleblowerform.controller

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.android_traffic.R
import com.example.android_traffic.databinding.FragmentWhistleblowerFormBinding
import com.example.android_traffic.whistleblowerform.viewModel.WhistleblowerFormViewModel
import java.io.File
import java.util.*

class WhistleblowerFormFragment : Fragment() {
    private lateinit var binding: FragmentWhistleblowerFormBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().title = "檢舉表單"
        binding = FragmentWhistleblowerFormBinding.inflate(inflater, container, false)
        val viewModel: WhistleblowerFormViewModel by viewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            arguments?.let {
                it.getSerializable("photo")?.let { file ->
                    val phtoPath = file as String
                    val path = File(phtoPath)
                    val bitmap = BitmapFactory.decodeFile(path.absolutePath)
                    ivPhotoWhistleblower.setImageBitmap(bitmap)
                }
            }
            viewModel?.run {
                tvWhistleblowerDate.setOnClickListener {
                    val calendar = Calendar.getInstance()
                    val datePickerDialog =
                        DatePickerDialog(   //呼叫DatePickerDialog這個建構式
                            requireContext(),
                            { _, year, month, day ->  //{}裡面是監聽器的實作   //user在日曆上按的的年/月/日
                                // 一月的值是0而非1，所以「month + 1」後才顯示
                                whistleblower.value?.violationsDate =
                                    "$year-${pad(month + 1)}-${pad(day)}"
                                binding.tvWhistleblowerDate.text =
                                    whistleblower.value?.violationsDate
                                println(whistleblower.value?.violationsDate)
                            },
                            calendar.get(Calendar.YEAR),  //當下的年//一開始預選的年/月/日, 這邊把當天當作是預選的那天
                            calendar.get(Calendar.MONTH), //當下的月
                            calendar.get(Calendar.DAY_OF_MONTH) //當下的日
                        )
                    // 取得DatePicker物件方可設定可選取的日期區間
                    //如果這一段L47~L54沒有設定的話,user所有的日期都可以選
//                val datePicker = datePickerDialog.datePicker
//                // 設定可選取的開始日為今日
//                datePicker.minDate = calendar.timeInMillis   //要設定可以給user選的最小的那天-當天(起始日期) //calendar.是當下日期時間
//                // 設定可選取的結束日為一個月後
//                calendar.add(Calendar.MONTH, 1)  //限定//加了1個月,所以user最多可以選到當下後的一個月
//                datePicker.maxDate = calendar.timeInMillis  //要設定可以給user選的最大的那天 //calendar.是當下日期時間
                    // 最後要呼叫show()方能顯示
                    datePickerDialog.show()

                }

                tvWhistleblowerTime.setOnClickListener {

                    val calendar = Calendar.getInstance()  //取得當下日期&時間
                    TimePickerDialog(
                        requireContext(),
                        { _, hour, minute ->
                            whistleblower.value?.violationsTime = "${pad(hour)}:${pad(minute)}"
                            binding.tvWhistleblowerTime.text = whistleblower.value?.violationsTime
                            println(whistleblower.value?.violationsTime)
                        },
                        calendar.get(Calendar.HOUR),
                        calendar.get(Calendar.MINUTE),
                        false
                    ).show()


                }

                btnWhistleblowerNext.setOnClickListener {

                    var whistleblowerFormAreFilled = true

                    //違規時間
//                    if (whistleblower.value?.violationsTime.isNullOrEmpty()) {
//                        edtTxtViolationTimeWhistleblower.error = "請輸入違規時間"
//                        whistleblowerFormAreFilled = false
//                    }
                    //違規車種
                    if (whistleblower.value?.vehicleType.isNullOrEmpty()) {
                        edtTxtVehicleTypeWhistleblower.error = "請輸入違規車號"
                        whistleblowerFormAreFilled = false
                    }
                    //違規地點   ok
                    if (whistleblower.value?.violationsAddress.isNullOrEmpty()) {
                        edtTxtViolationLocationWhistleblower.error = "請輸入違規地點"
                        whistleblowerFormAreFilled = false
                    }
                    //違規種類 ok
                    if (whistleblower.value?.violations.isNullOrEmpty()) {
                        edtTxtViolationsWhistleblower.error = "請輸入違規種類"
                        whistleblowerFormAreFilled = false
                    }
                    //地點備註 ok
                    if (whistleblower.value?.remark.isNullOrEmpty()) {
                        edtTxtRemarkWhistleblower.error = "請輸入地點備註"
                        whistleblowerFormAreFilled = false
                    }
                    //違規日期
//                    if (whistleblower.value?.violationsDate.isNullOrEmpty()) {
//                        edtTxtViolationFactWhistleblower.error = "請輸入違規日期"
//                        whistleblowerFormAreFilled = false
//                    }
                    //違規車號
                    if (whistleblower.value?.vehicleNo.isNullOrEmpty()) {
                        edtTxtVehicleNoWhistleblower.error = "請輸入違車牌"
                        whistleblowerFormAreFilled = false
                    }
                    if (whistleblowerFormAreFilled) {

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

    private fun pad(number: Int): String {   //填補作用,如果是個位數字,要補0  //這個是用在顯示的textView那邊
        return if (number >= 10) {
            number.toString()
        } else {
            "0$number"
        }
    }
}