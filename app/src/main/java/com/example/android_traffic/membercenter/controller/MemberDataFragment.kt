package com.example.android_traffic.membercenter.controller

import android.app.Activity
import android.app.AlertDialog
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
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.android_traffic.R
import com.example.android_traffic.core.model.Member
import com.example.android_traffic.core.service.Server
import com.example.android_traffic.core.service.requestTask
import com.example.android_traffic.core.util.getImgBase64
import com.example.android_traffic.databinding.FragmentMemberDataBinding
import com.example.android_traffic.membercenter.viewmodel.MemberDataViewModel
import com.google.gson.JsonObject
import com.yalantis.ucrop.UCrop
import java.io.File

class MemberDataFragment : Fragment() {
    private lateinit var binding: FragmentMemberDataBinding
    private lateinit var contentUri: Uri // 拍照用的Uri
    private val editData: Member = Member()
//    private var type: Int? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: MemberDataViewModel by viewModels()
        binding = FragmentMemberDataBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            viewModel?.init()
            activity?.title = getString(R.string.txt_MemberData_Title)

            //修改頭像
            ivMemberDataAvatar.setOnClickListener {
                alertDialogPicture(requireContext())

            }
            //進入車輛資料
            clMemberDataVehidedata.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(
                        R.id.action_memberDataFragment_to_memberDataVehideDataFragment
                    )
            }
        }
    }


    private fun alertDialogPicture(context: Context) {
        //alertDialog
        val dialogView = LayoutInflater.from(context)
            .inflate(R.layout.membercent_memeberdata_picture_alertdialog, null)
        val alertDialogBuilder = AlertDialog.Builder(context, R.style.AlertDialogCustomStyle)
            .setView(dialogView)
        val alertDialog = alertDialogBuilder.create()

        // 自訂布局李的按鈕監聽
        val takePictureButton = dialogView.findViewById<TextView>(R.id.tv_MemberData_TakePictrue)
        val pickPictureButton = dialogView.findViewById<TextView>(R.id.tv_MemberData_PickPictrue)

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
                            binding.viewModel?.member?.value?.avatarBase64 =
                                binding.ivMemberDataAvatar.getImgBase64()
                            editData?.avatarBase64 = binding.viewModel?.member?.value?.avatarBase64
                            val respBody = requestTask<JsonObject>(Server.url, "PUT", editData)
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
