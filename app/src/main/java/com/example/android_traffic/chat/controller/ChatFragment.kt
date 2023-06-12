package com.example.android_traffic.chat.controller

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_traffic.chat.viewmodel.ChatViewModel
import com.example.android_traffic.core.model.Chat
import com.example.android_traffic.core.model.ChatRoom
import com.example.android_traffic.core.service.Server.Companion.urlChat
import com.example.android_traffic.core.service.requestTask
import com.example.android_traffic.databinding.FragmentChatBinding
import com.example.android_traffic.ticket.model.Token
import com.google.gson.JsonObject
import java.io.ByteArrayOutputStream
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
//        (requireActivity() as MainActivity).supportActionBar?.hide()
        val viewmodel: ChatViewModel by viewModels()
        binding = FragmentChatBinding.inflate(inflater, container, false)
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fun loadPreferences() {
            with(binding) {
                val preferences = Token().getEncryptedPreferences(requireContext())
                viewmodel?.member?.value = preferences.getString("MemId", "")
                val myTag = "TAG_${javaClass.simpleName}"
                Log.d(
                    myTag,
                    "getString: ${preferences.getString("MemId", "")?.javaClass?.simpleName}"
                )
            }
        }
        with(binding) {
//            activity?.title = "${viewmodel!!.chat.value!!.nickname}"
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
            viewmodel?.getNewChat()

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
//                tvChatText.visibility = View.GONE
//                ivChatAppendix.visibility = View.VISIBLE
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

//                tvChatText.visibility = View.GONE
//                ivChatAppendix.visibility = View.VISIBLE
            }

//            ivChatAppendix.setOnLongClickListener {
//                ivChatAppendix.setImageDrawable(null)
//                tvChatText.visibility = View.VISIBLE
//                true
//            }

            imgBtnChatSend.setOnClickListener {
                if (tvChatText.text.isEmpty()) {
                    Toast.makeText(requireContext(), "請輸入文字", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                } else {
                    val chatmessage = Chat(
                        senderID = this.viewmodel!!.member!!.value!!.toInt(),
                        chatroomID = this.viewmodel!!.chatroom!!.value!!.ID!!,
                        content = tvChatText.text.toString()
                    )
                    requestTask<JsonObject>("${urlChat}", "POST", chatmessage)
                    tvChatText.text = null
                }
            }
        }
    }

    private var takePictureLargeLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            // Android 9之前使用BitmapFactory；Android 9開始使用ImageDecoder
            if (result.resultCode == Activity.RESULT_OK) {
                with(binding) {

//                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
//                        val bitmap = BitmapFactory.decodeFile(camerafile.path)
//                        ivChatAppendix.setImageBitmap(bitmap)
//                    } else {
//                        val listener =
//                            ImageDecoder.OnHeaderDecodedListener { decoder, info, source ->
//                                val mimeType = info.mimeType
//                                val width = info.size.width
//                                val height = info.size.height
//                            }
//                        // 取得圖片來源
//                        val source = ImageDecoder.createSource(camerafile)
//                        try {
//                            // 取得Bitmap並顯示
//                            val bitmap = ImageDecoder.decodeBitmap(source, listener)
//                            ivChatAppendix.setImageBitmap(bitmap)
//                        } catch (e: IOException) {
////                            Log.e(myTag, e.toString())
//                        }
//                    }

                    val options = BitmapFactory.Options()
                    options.inSampleSize = 3
                    val bitmap = BitmapFactory.decodeFile(camerafile.absolutePath)
                    val byteArrayOutputStream = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream)
                    val byteArray = byteArrayOutputStream.toByteArray()
                    val chatmessage = Chat(
                        senderID = viewmodel!!.member!!.value!!.toInt(),
                        chatroomID = viewmodel!!.chatroom!!.value!!.ID!!,
                        appendix = byteArray
                    )
                    requestTask<JsonObject>("${urlChat}", "POST", chatmessage)
                }
            }
        }

    private var pickPictureLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let {
//                        uri ->
//                    binding.ivChatAppendix.setImageURI(uri)
                    val options = BitmapFactory.Options()
                    options.inSampleSize = 1
                    val inputStream = requireActivity().contentResolver.openInputStream(it)
                    val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
                    inputStream?.close()
                    val byteArrayOutputStream = ByteArrayOutputStream()
                    bitmap?.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream)
                    val byteArray = byteArrayOutputStream.toByteArray()
                    val chatmessage = Chat(
                        senderID = binding.viewmodel!!.member!!.value!!.toInt(),
                        chatroomID = binding.viewmodel!!.chatroom!!.value!!.ID!!,
                        appendix = byteArray
                    )
                    requestTask<JsonObject>("${urlChat}", "POST", chatmessage)

                }
            }
        }

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