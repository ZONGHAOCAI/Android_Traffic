//package com.example.android_traffic.chat.controller
//
//import android.app.Activity
//import android.content.Intent
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.net.Uri
//import android.os.Bundle
//import android.provider.MediaStore
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.core.content.FileProvider
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import com.example.android_traffic.R
//import com.example.android_traffic.chat.Util.ChatMessage
//import com.example.android_traffic.chat.viewmodel.ChatViewModel
//import com.example.android_traffic.databinding.FragmentChatBinding
//import java.io.File
//
//class ChatInsertMessage : Fragment() {
//    private val myTag = "TAG_${javaClass.simpleName}"
//    private lateinit var binding: FragmentChatBinding
//    private lateinit var contentUri: Uri // 拍照需要的Uri
//    private var cropImageUri: Uri? = null // 截圖的Uri
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View {
////        requireActivity().title = getString(R.string.txtInsert)
//        val viewModel: ChatViewModel by viewModels()
//        binding = FragmentChatBinding.inflate(inflater, container, false)
//        binding.viewmodel = viewModel
//        binding.lifecycleOwner = this
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        with(binding) {
//            viewmodel?.message?.value = ChatMessage()
//            imgBtnChatCamera.setOnClickListener {
//                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//                val file = File(requireContext().getExternalFilesDir(null), "picture.jpg")
//                contentUri = FileProvider.getUriForFile(
//                    requireContext(), requireContext().packageName, file
//                )
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri)
//                takePictureLauncher.launch(intent)
//            }
//
//            imgBtnChatAlbum.setOnClickListener {
//                val intent = Intent(
//                    Intent.ACTION_PICK,
//                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//                )
//                pickPictureLauncher.launch(intent)
//            }
//
////            btCancel.setOnClickListener {
////                findNavController().popBackStack()
////            }
//
////            新增按鈕( = 建立一個旅遊景點)
//            btFinishInsert.setOnClickListener {
//                with(viewModel!!) vm@{
//                    // 設定spot ID
//                    this.setSpotId()
//                    val spot = spot.value!!
//                    // 為了確保要先新增完成，才能返回前頁，所以使用withContext，而且必須置於coroutine內
//                    lifecycleScope.launch {
//                        withContext(Dispatchers.Main) {
//                            // 判斷是否有圖
//                            if (cropImageUri == null) {
//                                // 太多scope都有相同的this，需要自訂scope名稱，否則會產生警示訊息
//                                this@vm.addOrReplace(spot)
//                                findNavController().popBackStack()
//                            } else {
//                                val imagePath = "${getString(R.string.app_name)}/images/${spot.id}"
//                                Log.d(myTag, "imagePath: $imagePath")
//                                spot.imagePath = imagePath
//                                this@vm.addOrReplace(spot)
//                                FirebaseStorage.getInstance()
//                                    .reference.child(imagePath)
//                                    .putFile(cropImageUri!!).addOnCompleteListener { task ->
//                                        if (task.isSuccessful) {
//                                            findNavController().popBackStack()
//                                        }
//                                    }
//                            }
//                        }
//                        // 等待withContext區塊內執行完畢才會向下執行
//                        Toast.makeText(requireContext(), R.string.txtInserted, Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                }
//            }
//        }
//    }
//
//    private var takePictureLauncher =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                crop(contentUri)
//            }
//        }
//
//    private var pickPictureLauncher =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                result.data?.data?.let { uri -> crop(uri) }
//            }
//        }
//
//    private fun crop(sourceImageUri: Uri) {
//        val file = File(requireContext().getExternalFilesDir(null), "picture_cropped.jpg")
//        val destinationUri = Uri.fromFile(file)
//        val cropIntent: Intent = UCrop.of(
//            sourceImageUri,
//            destinationUri
//        )
//            // .withAspectRatio(16, 9) // 設定裁減比例
//            // .withMaxResultSize(500, 500) // 設定結果尺寸不可超過指定寬高
//            .getIntent(requireContext())
//        cropPictureLauncher.launch(cropIntent)
//    }
//
//    private var cropPictureLauncher =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                var bitmap: Bitmap? = null
//                result.data?.let { intent ->
//                    UCrop.getOutput(intent)?.let { uri ->
//                        cropImageUri = uri
//                        bitmap = BitmapFactory.decodeStream(
//                            requireContext().contentResolver.openInputStream(uri)
//                        )
//                    }
//                }
//                with(binding) {
//                    if (bitmap != null) {
//                        ivSpot.setImageBitmap(bitmap)
//                    } else {
//                        ivSpot.setImageResource(R.drawable.no_image)
//                    }
//                }
//            }
//        }
//}