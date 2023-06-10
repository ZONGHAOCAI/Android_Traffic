package com.example.android_traffic

import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.android_traffic.databinding.FragmentPhotoBinding
import com.example.android_traffic.databinding.FragmentTicketUnpaidListBinding
import com.example.android_traffic.ticket.model.Content
import com.example.android_traffic.ticket.viewmodel.TicketUnpaidListViewModel
import java.io.IOException

class PhotoFragment : Fragment() {
    private lateinit var binding: FragmentPhotoBinding
    private val myTag = "TAG_${javaClass.simpleName}"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewmodel: PhotoViewModel by viewModels()
        binding = FragmentPhotoBinding.inflate(inflater, container, false)
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            it.getSerializable("photo")?.let { file ->
                Log.e(myTag, "file: ${file}")
                val photoPath = file as String
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
                    val bitmap = BitmapFactory.decodeFile(file)
                    binding.ivPhotoImg.setImageBitmap(bitmap)
                }
//                else {
//                    val listener =
//                        ImageDecoder.OnHeaderDecodedListener { decoder, info, source ->
//                            val mimeType = info.mimeType
//                            val width = info.size.width
//                            val height = info.size.height
//                        }
//                    // 取得圖片來源
//                    val source = ImageDecoder.createSource(file)
//                    try {
//                        // 取得Bitmap並顯示
//                        val bitmap = ImageDecoder.decodeBitmap(source, listener)
//                        binding.ivPhotoImg.setImageBitmap(bitmap)
//                    } catch (e: IOException) {
//                        Log.e(myTag, e.toString())
//                    }
//                }
            }
        }
    }

}