package com.example.android_traffic.membercenter.controller

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_traffic.R
import com.example.android_traffic.core.model.Member
import com.example.android_traffic.membercenter.viewmodel.MemberDataViewModel
import com.example.android_traffic.databinding.FragmentMemberDataBinding
import com.example.android_traffic.membercenter.adapter.MemberDataListAdapter
import com.yalantis.ucrop.UCrop
import java.io.File

class MemberDataFragment : Fragment() {
    private lateinit var binding: FragmentMemberDataBinding
    private lateinit var contentUri: Uri // 拍照用的Uri
    private var cropImageUri: Uri? = null // 截圖用的Uri
    private val editData: Member = Member()
    private var type: Int? = null
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
        with(binding){
            viewModel?.init()
            activity?.title = getString(R.string.txt_MemberData_Title)
            //修改頭像
            clMemberDataAvatar.setOnClickListener {  }
            //修改姓名
            clMemberDataVehidedata.setOnClickListener {
//                var bundle = Bundle()
//                bundle.putSerializable("memberID", memberData.id)

                    Navigation.findNavController(it)
                        .navigate(
                            R.id.action_memberDataFragment_to_memberDataVehideDataFragment
                        )

            }

        }

    }

    var takePictureLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                crop(contentUri)
            }
        }

    var pickPictureLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri -> crop(uri) }
            }
        }
    private fun crop(sourceImageUri: Uri) {
        val file = File(requireContext().getExternalFilesDir(null), "picture_cropped.jpg")
        val destinationUri = Uri.fromFile(file)
        val cropIntent: Intent = UCrop.of(
            sourceImageUri,
            destinationUri
        )
            // .withAspectRatio(16, 9) // 設定裁減比例
            // .withMaxResultSize(500, 500) // 設定結果尺寸不可超過指定寬高
            .getIntent(requireContext())
        cropPictureLauncher.launch(cropIntent)
    }
    private var cropPictureLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                var bitmap: Bitmap? = null
                result.data?.let { intent ->
                    UCrop.getOutput(intent)?.let { uri ->
                        cropImageUri = uri
                        bitmap = BitmapFactory.decodeStream(
                            requireContext().contentResolver.openInputStream(uri)
                        )
                    }
                }

//                    if (bitmap != null) {
//                        binding.ivSpot.setImageBitmap(bitmap)
//                    } else {
//                        ivSpot.setImageResource(R.drawable.no_image)
//                    }

            }
        }
}
