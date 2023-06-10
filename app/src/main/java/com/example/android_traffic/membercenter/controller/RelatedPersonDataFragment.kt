package com.example.android_traffic.membercenter.controller


import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.android_traffic.R
import com.example.android_traffic.core.model.Member

import com.example.android_traffic.databinding.FragmentRelatedPersonDataBinding
import com.example.android_traffic.core.model.RelatedPerson
import com.example.android_traffic.core.service.Server
import com.example.android_traffic.core.service.requestTask
import com.example.android_traffic.core.util.getImgBase64
import com.example.android_traffic.membercenter.viewmodel.RelatedPersonDataViewModel
import com.google.gson.JsonObject
import com.yalantis.ucrop.UCrop
import java.io.File
import java.util.*

class RelatedPersonDataFragment : Fragment() {
    private lateinit var binding: FragmentRelatedPersonDataBinding
    private lateinit var contentUri: Uri // 拍照用的Uri
    private val editData: RelatedPerson = RelatedPerson()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 呈現標題列
//        (requireActivity() as MainActivity).supportActionBar?.show()
        val viewModel: RelatedPersonDataViewModel by viewModels()
        binding = FragmentRelatedPersonDataBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {


            arguments?.let { bundle ->
                bundle.getSerializable("relatedPerson")?.let {
                    binding.viewModel?.relatedPerson?.value = it as RelatedPerson
                    editData.id = viewModel?.relatedPerson?.value?.id

                }
            }

            //修改頭像
            imgRelatedPersonDataAvatar.setOnClickListener {
                alertDialogPicture(requireContext())
            }

            //修改姓名
            clRelatedPersonDataName.setOnClickListener {
                viewModel?.relatedPerson?.value?.let { it1 -> sendBundle("Name", it) }
            }

            //修改關係
            clRelatedPersonDataRelatedShip.setOnClickListener {
                viewModel?.relatedPerson?.value?.let { it1 -> sendBundle("Relationship", it) }
            }

            //修改身分證
            clRelatedPersonDataIdentityNumber.setOnClickListener {
                viewModel?.relatedPerson?.value?.let { it1 -> sendBundle("IdentityNumber", it) }
            }

            //生日 跳出選日期視窗 並轉成民國
            clRelatedPersonDataBirthday.setOnClickListener {
                showDatePickerDialog(it)
//                editBirthday(it, pickDate)
            }
            //進車輛資料
            clRelatedPersonDataVehicleData.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("ID", viewModel?.relatedPerson?.value?.id)
                Navigation.findNavController(it).navigate(R.id.action_relatedPersonDataFragment_to_relatedPersonDataVehideDataFragment, bundle)
            }
        }
    }

    private fun sendBundle(type: String, view: View) {
        val bundle = Bundle()
        bundle.putSerializable("type", type)
        bundle.putSerializable("relatedPersonData", binding.viewModel?.relatedPerson?.value)
        Navigation.findNavController(view).navigate(
            R.id.action_relatedPersonDataFragment_to_relatedPersonDataEditFragment, bundle)
    }

    private fun editBirthday(pickDate: String) {
        editData.birthday = pickDate
        val respBody = requestTask<JsonObject>(Server.urlFindRelatedperson, "PUT", editData)
        respBody?.run {
            if (get("successful").asBoolean) {
            }
        }
        editData.birthday = ""
        binding.tvRelatedPersonDataBirthday.text = pickDate
    }
    /** 跳出選日期的視窗 */
    private fun showDatePickerDialog(view: View){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        var rocDate = ""
        val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
            // 西元轉民國
            val rocYear = selectedYear - 1911
//            val formattedRocYear = String.format("%02d", rocYear) // 補足年份的位數
            var yy = ""
            if (rocYear < 100) {
                yy = "0$rocYear"
            } else yy = rocYear.toString()

            val formattedMonth = String.format("%02d", selectedMonth + 1) // 補足月份的位數
            val formattedDay = String.format("%02d", selectedDay) // 補足日期的位數

            rocDate = "${yy}-${formattedMonth}-${formattedDay}"
            // 執行相應的操作，如顯示選擇的日期


//            binding.viewModel?.member?.value?.birthday = rocDate

            editBirthday(rocDate)
        }, year, month, day)

        datePickerDialog.show()
        binding.viewModel?.relatedPerson?.value?.birthday = rocDate
    }
    private fun alertDialogPicture(context: Context) {
        //alertDialog
        val dialogView = LayoutInflater.from(context)
            .inflate(R.layout.membercent_memeberdata_picture_alertdialog, null)
        val alertDialogBuilder = AlertDialog.Builder(context, R.style.AlertDialogCustomStyle)
            .setView(dialogView)
        val alertDialog = alertDialogBuilder.create()

        // 自訂布局李的按鈕監聽
        val takePictureButton =
            dialogView.findViewById<TextView>(R.id.tv_MemberData_TakePictrue)
        val pickPictureButton =
            dialogView.findViewById<TextView>(R.id.tv_MemberData_PickPictrue)

        //拍照按鈕的監聽
        takePictureButton.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val file = File(requireContext().getExternalFilesDir(null), "picture.jpg")
            contentUri = FileProvider.getUriForFile(
                requireContext(), requireContext().packageName, file
            )
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri)
            takePictureLauncher.launch(intent)
            alertDialog.dismiss()
        }

        //相簿按鈕的監聽
        pickPictureButton.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            pickPictureLauncher.launch(intent)
            alertDialog.dismiss()
        }
        alertDialog.show()
    }

    //拍照
    private var takePictureLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                crop(contentUri)
            }
        }

    //相簿
    private var pickPictureLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri -> crop(uri) }
            }
        }

    private fun crop(sourceImageUri: Uri) {
        val file = File(requireContext().getExternalFilesDir(null), "picture_cropped.jpg")
        val destinationUri = Uri.fromFile(file)

        //給拍完照的裁剪設圓型的設定
        val options = UCrop.Options()
        options.setCircleDimmedLayer(true)

        val cropIntent: Intent = UCrop.of(

            sourceImageUri,
            destinationUri
        )
            .withAspectRatio(1F, 1F) // 設定裁減比例
//            .withMaxResultSize(500, 500) // 設定結果尺寸不可超過指定寬高
            .withOptions(options)
            .getIntent(requireContext())
        cropPictureLauncher.launch(cropIntent)
    }


    private var cropPictureLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let { intent ->
                    UCrop.getOutput(intent)?.let { uri ->
                        val bitmap = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
                            BitmapFactory.decodeStream(
                                requireContext().contentResolver.openInputStream(uri)
                            )
                        } else {
                            val source = ImageDecoder.createSource(
                                requireContext().contentResolver, uri
                            )
                            ImageDecoder.decodeBitmap(source)
                        }
                        // 有圖片即顯示，沒圖片則套用no_image圖片
                        if (bitmap != null) {
                            binding.imgRelatedPersonDataAvatar.setImageBitmap(bitmap)
                            binding.viewModel?.relatedPerson?.value?.avatarBase64 =
                                binding.imgRelatedPersonDataAvatar.getImgBase64()
                            editData?.avatarBase64 =
                                binding.viewModel?.relatedPerson?.value?.avatarBase64
                            Log.d("hihi", editData.id.toString())
                            val respBody = requestTask<JsonObject>(Server.urlFindRelatedperson, "PUT", editData)
                            respBody?.run {
                                if (get("successful").asBoolean) {
                                    println("圖片上傳成功")
                                }
                            }
                        }
                    }
                }
            }
        }
}