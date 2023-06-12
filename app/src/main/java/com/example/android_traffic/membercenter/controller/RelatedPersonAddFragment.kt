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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.android_traffic.R
import com.example.android_traffic.core.service.Server.Companion.urlFindRelatedperson
import com.example.android_traffic.core.service.requestTask
import com.example.android_traffic.core.util.getImgBase64
import com.example.android_traffic.databinding.FragmentRelatedPersonAddBinding
import com.example.android_traffic.databinding.FragmentRelatedPersonBinding
import com.example.android_traffic.membercenter.viewmodel.RelatedPersonViewModel
import com.google.gson.JsonObject
import com.yalantis.ucrop.UCrop
import java.io.File
import java.util.*

class RelatedPersonAddFragment : Fragment() {
    private lateinit var binding: FragmentRelatedPersonAddBinding
    private lateinit var contentUri: Uri // 拍照用的Uri


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: RelatedPersonViewModel by viewModels()
        binding = FragmentRelatedPersonAddBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            activity?.title = "新增關係人"


            //新增頭像
            ivMemberDataAvatar.setOnClickListener {
                alertDialogPicture(requireContext())
            }

            //新增日期
            ibtnRelatedPersonAddBirthday.setOnClickListener {
                showDatePickerDialog(it)
            }

            btnRelatedPersonAddRegister.setOnClickListener {

                if (viewModel?.relatedPersonN?.value?.name?.isEmpty() == true) {
                    etRelatedPersonAddName.error = getString(R.string.txt_MemberData_Edit_Error)
                    return@setOnClickListener
                }
                if (viewModel?.relatedPersonN?.value?.identityNumber?.isEmpty() == true) {
                    etRelatedPersonAddIdentityNumber.error =
                        getString(R.string.txt_MemberData_Edit_Error)
                    return@setOnClickListener
                }
                if (viewModel?.relatedPersonN?.value?.memberRelationship?.isEmpty() == true) {
                    tvRelatedPersonAddBirthday.error =
                        getString(R.string.txt_MemberData_Edit_Error)
                    return@setOnClickListener
                }
                if (viewModel?.relatedPersonN?.value?.birthday?.isEmpty() == true) {
                    tvRelatedPersonAddBirthday.error =
                        getString(R.string.txt_MemberData_Edit_Error)
                    return@setOnClickListener
                }
                val respBody = requestTask<JsonObject>(
                    urlFindRelatedperson, "POST", viewModel?.relatedPersonN?.value
                )

                viewModel?.change()

                respBody?.run {
                    if (get("successful").asBoolean) {
                        println("關係人新增成功")
                        Navigation.findNavController(it).popBackStack()
                        Toast.makeText(requireContext(), "新增成功", Toast.LENGTH_SHORT ).show()
                    }

                }


            }

        }
    }


    /** 跳出選日期的視窗 */
    private fun showDatePickerDialog(view: View): String {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        var rocDate = ""
        val datePickerDialog =
            DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
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
                binding.tvRelatedPersonAddBirthday.text = rocDate
//            binding.viewModel?.relatedPerson?.value?.birthday = rocDate

            }, year, month, day)

        datePickerDialog.show()
        return rocDate

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
                            binding.ivMemberDataAvatar.setImageBitmap(bitmap)
                            binding.viewModel?.relatedPersonN?.value?.avatarBase64 =
                                binding.ivMemberDataAvatar.getImgBase64()
                        }
                    }
                }
            }
        }
}