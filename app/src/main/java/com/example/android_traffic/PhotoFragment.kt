package com.example.android_traffic

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.example.android_traffic.databinding.FragmentPhotoBinding
import com.example.android_traffic.databinding.FragmentTicketUnpaidListBinding
import com.example.android_traffic.ticket.model.Content
import com.example.android_traffic.ticket.viewmodel.TicketUnpaidListViewModel
import java.io.File
import java.io.IOException
import java.util.Base64

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){

            arguments?.let {
                it.getSerializable("photo")?.let { file ->
                    Log.d(myTag, "file: ${file}")
                    val phtoPath = file as String
                    val path = File(phtoPath)
                    val bitmap = BitmapFactory.decodeFile(path.absolutePath)
                    ivPhotoImg.setImageBitmap(bitmap)
                }
            }
        }
    }
}