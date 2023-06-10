package com.example.android_traffic

import android.annotation.SuppressLint
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
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android_traffic.databinding.FragmentMainBinding
import com.example.android_traffic.ticket.model.Token
import java.io.File
import java.io.IOException

class MainFragment : Fragment() {
    private val myTag = "TAG_${javaClass.simpleName}"
    private lateinit var binding: FragmentMainBinding
    private lateinit var file: File

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewmodel: MainViewModel by viewModels()
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {

            if (viewmodel?.memberId == null){
                Navigation.findNavController(view).navigate(R.id.loginFragment)
            }
            imgBtnMainPhoto.setOnClickListener {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                file = File(requireContext().getExternalFilesDir(null), "picture.jpg")
                // Android 7開始，指定拍照存檔路徑要改使用FileProvider
                // 這個是暫存目錄，確認沒問題才實際傳到正確位置
                val contentUri = FileProvider.getUriForFile(
                    //                暫存路徑          真實路徑
                    requireContext(), requireContext().packageName, file
                )
                Log.d(myTag, "file: ${file}")
                // 拍照前指定存檔路徑就可取得原圖而非縮圖
                intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri)
                Log.d(
                    myTag, "putExtra: ${intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri)}"
                )
                val bundle = Bundle()
                bundle.putSerializable("photo", file.path)
                Log.d(myTag, "bundle: ${bundle}")
                takePictureLargeLauncher.launch(intent)

                Navigation.findNavController(view).navigate(R.id.whistleblowerFormFragment, bundle)
            }
            imgBtnMainAppealHistory.setOnClickListener {
                findNavController().navigate(R.id.memberCenterFragment)
            }
            imgBtnMainTicket.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_mainFragment_to_ticketFragment)
            }
            imgBtnMainChat.setOnClickListener {
                Navigation.findNavController(it)
                .navigate(R.id.action_mainFragment_to_chatRoomFragment)
            }
            imgBtnMainForum.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_mainFragment_to_forumFragment)
            }
            imgBtnMainAppeal.setOnClickListener {
                findNavController().navigate(R.id.whistleblowerFormFragment)
            }
        }
        saveCreditCard()
    }

    private var takePictureLargeLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            // Android 9之前使用BitmapFactory；Android 9開始使用ImageDecoder
            if (result.resultCode == Activity.RESULT_OK) {
                with(binding) {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
                        val bitmap = BitmapFactory.decodeFile(file.path)
                        imageView.setImageBitmap(bitmap)

                    } else {
                        val listener =
                            ImageDecoder.OnHeaderDecodedListener { decoder, info, source ->
                                val mimeType = info.mimeType
                                val width = info.size.width
                                val height = info.size.height
                            }
                        // 取得圖片來源
                        val source = ImageDecoder.createSource(file)
                        try {
                            // 取得Bitmap並顯示
                            val bitmap = ImageDecoder.decodeBitmap(source, listener)
                            imageView.setImageBitmap(bitmap)
                        } catch (e: IOException) {
                            Log.e(myTag, e.toString())
                        }
                    }
                }
            }
        }

    /**
     * 將信用卡資料存入偏好設定(加密)
     */
    @SuppressLint("SuspiciousIndentation")
    fun saveCreditCard() {
        with(binding) {
            //取Key如果為null則新增 else 改名避免覆蓋 FIXME
            val preferences = Token().getEncryptedPreferences(requireContext())
            val txt  = preferences.getString("flag1","")

                preferences.edit()
//                    .putString("flag1","flag1")//塞入旗標
                    .putString("MemId", viewmodel?.memberId.toString())
                    .apply()

            //FIXME 改為一次單筆，但又能有不同KEY 然後好查詢，不要用亂數或是currentTimeMillis
//            Toast.makeText(context, "新增完成", Toast.LENGTH_SHORT).show()
        }
    }
}