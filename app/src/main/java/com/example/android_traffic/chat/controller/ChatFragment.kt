package com.example.android_traffic.chat.controller

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_traffic.MainActivity
import com.example.android_traffic.chat.viewmodel.ChatViewModel
import com.example.android_traffic.core.model.ChatRoom
import com.example.android_traffic.databinding.FragmentChatBinding
import com.example.android_traffic.ticket.model.Token
import java.io.File
import java.io.IOException

class ChatFragment : Fragment() {
    private val myTag = "TAG_${javaClass.simpleName}"
    private lateinit var binding: FragmentChatBinding
    private lateinit var camerafile: File

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        // 將標題隱藏
        (requireActivity() as MainActivity).supportActionBar?.hide()
        val viewmodel: ChatViewModel by viewModels()
        binding = FragmentChatBinding.inflate(inflater, container, false)
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            arguments?.let {
                it.getSerializable("nickname")?.let {
                    binding.viewmodel?.chatroom?.value = it as ChatRoom
                }
            }
            // 建立偏好設定物件
            val preferences = Token().getEncryptedPreferences(requireContext())

            loadPreferences()
            rvChatContent.layoutManager = LinearLayoutManager(requireContext())
            viewmodel?.init()
            viewmodel?.list?.observe(viewLifecycleOwner) {
                ChatAdapter(it).setSharedPreferences(preferences)
                if (rvChatContent.adapter == null) {
                    rvChatContent.adapter = ChatAdapter(it)
//                        這個方法也可以，是以堆疊顯示，但是會有畫面跳轉的畫面，如果資料多會跳很快
//                        rvChatContent.layoutManager = LinearLayoutManager(requireContext()).apply {
//                            stackFromEnd = true
                    rvChatContent.post {
                        rvChatContent.scrollToPosition(it.size - 1)
                    }
                } else {
                    (rvChatContent.adapter as ChatAdapter).updateChatContentList(it)
                    rvChatContent.post {
                        rvChatContent.smoothScrollToPosition(it.size - 1)
                    }
                }
            }

            imgBtnChatCamera.setOnClickListener {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                camerafile = File(requireContext().getExternalFilesDir(null), "picture.jpg")
                // Android 7開始，指定拍照存檔路徑要改使用FileProvider
                val contentUri = FileProvider.getUriForFile(
                    requireContext(), requireContext().packageName, camerafile
                )
                // 拍照前指定存檔路徑就可取得原圖而非縮圖
                intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri)
                Log.d(
                    "myTag_${javaClass.simpleName}",
                    "included2.tvalbumChat.setOnClickListener"
                )
                takePictureLargeLauncher.launch(intent)
                Log.d(
                    "myTag_ ${javaClass.simpleName}",
                    " takePictureLargeLauncher.launch(intent)"
                )
                tvChatText.visibility = View.GONE
                ivChatAppendix.visibility = View.VISIBLE
            }

            imgBtnChatAlbum.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                Log.d(
                    "myTag_${javaClass.simpleName}",
                    "included2.tvalbumChat.setOnClickListener"
                )
                pickPictureLauncher.launch(intent)
                Log.d("myTag_${javaClass.simpleName}", "pickPictureLauncher.launch(intent)")

                tvChatText.visibility = View.GONE
                ivChatAppendix.visibility = View.VISIBLE
            }

            ivChatAppendix.setOnLongClickListener {
                ivChatAppendix.visibility = View.GONE
                tvChatText.visibility = View.VISIBLE
                true
            }
        }


    }

    private fun loadPreferences() {
        with(binding) {
            val preferences = Token().getEncryptedPreferences(requireContext())
            viewmodel?.member?.value = preferences.getString("MemId", "")
            val myTag = "TAG_${javaClass.simpleName}"
            Log.d(myTag, "getString: ${preferences.getString("MemId", "")?.javaClass?.simpleName}")
        }
    }

    private var takePictureLargeLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            // Android 9之前使用BitmapFactory；Android 9開始使用ImageDecoder
            if (result.resultCode == Activity.RESULT_OK) {
                with(binding) {
                    var text = ""
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
                        val bitmap = BitmapFactory.decodeFile(camerafile.path)
                        ivChatAppendix.setImageBitmap(bitmap)
                        val width = bitmap.width
                        val height = bitmap.height
                        text = "圖片尺寸: $width x $height"
                    } else {
                        val listener =
                            ImageDecoder.OnHeaderDecodedListener { decoder, info, source ->
                                val mimeType = info.mimeType
                                val width = info.size.width
                                val height = info.size.height
                            }
                        // 取得圖片來源
                        val source = ImageDecoder.createSource(camerafile)
                        try {
                            // 取得Bitmap並顯示
                            val bitmap = ImageDecoder.decodeBitmap(source, listener)
                            ivChatAppendix.setImageBitmap(bitmap)
                        } catch (e: IOException) {
                            Log.e(myTag, e.toString())
                        }
                    }
                }
            }
        }

    //    private var takePictureLargeLauncher =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            Log.d("myTag_${javaClass.simpleName}", "takePictureLargeLauncher")
//
//            if (result.resultCode == Activity.RESULT_OK) {
//                Log.d(
//                    "myTag_${javaClass.simpleName}",
//                    "result.resultCode == Activity.RESULT_OK"
//                )
////                val list = binding.viewModel?.messagelist?.value ?: listOf()
////                val mutableList = list.toMutableList()
//                val chat: ChatContent
//                val options = BitmapFactory.Options()
//                options.inSampleSize = 3
//                val bitmap = BitmapFactory.decodeFile(camerafile.absolutePath)
//                val byteArrayOutputStream = ByteArrayOutputStream()
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream)
//                val byteArray = byteArrayOutputStream.toByteArray()
//                chat = ChatContent(
//                    senderID = binding.viewModel?.chatmaterial?.value!!.id,
//                            chatroomID = binding.viewModel?.chatmaterial?.value!!.id,
//                            appendix = byteArray
//                )
//                requestTask<JsonObject>(
//                    "http://10.0.2.2:8080/javaweb-Traffic/Chat/ChatController",
//                    method = "POST",
//                    reqBody = chat
//                )
//            }
//        }
//
    private var pickPictureLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri -> binding.ivChatAppendix.setImageURI(uri) }
            }
        }

//
//    private var pickPictureLauncher =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            Log.d("myTag_${javaClass.simpleName}", "result: $result")
//
//            if (result.resultCode == Activity.RESULT_OK) {
//                Log.d(
//                    "myTag_${javaClass.simpleName}",
//                    "result.resultCode == Activity.RESULT_OK"
//                )
//                result.data?.data?.let {
//                    Log.d("myTag${javaClass.simpleName}", "result ! null")
//                    val chat: ChatContent
//                    val options = BitmapFactory.Options()
//                    options.inSampleSize = 1
//
//                    val inputStream = requireActivity().contentResolver.openInputStream(it)
//                    val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
//                    inputStream?.close()
//                    val byteArrayOutputStream = ByteArrayOutputStream()
//
//                    bitmap?.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream)
//                    val byteArray = byteArrayOutputStream.toByteArray()
//                    chat = ChatContent(
//                        senderID = binding.viewModel?.chatmaterial?.value!!.id,
//                        chatroomID = binding.viewModel?.chatmaterial?.value!!.id,
//                        appendix = byteArray
//                    )
//                    requestTask<JsonObject>(
//                        "http://10.0.2.2:8080/javaweb-Traffic/Chat/ChatController",
//                        method = "POST",
//                        reqBody = chat
//                    )
//                }
//            }
//        }


//
//    //        裁切
//    private fun crop(sourceImageUri: Uri) {
//        Log.d("myTag${javaClass::getSimpleName}", "crop")
//        val file = File(requireContext().getExternalFilesDir(null), "picture_cropped.jpg")
//        val destinationUri = Uri.fromFile(file)
//        val cropIntent: Intent = UCrop.of(
//            sourceImageUri,
//            destinationUri
//        )
//            .getIntent(requireContext())
//        cropPictureLauncher.launch(cropIntent)
//    }
//
//    private var cropPictureLauncher =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//        }
}