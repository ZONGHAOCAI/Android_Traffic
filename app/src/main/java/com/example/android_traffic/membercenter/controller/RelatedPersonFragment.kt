package com.example.android_traffic.membercenter.controller


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.android_traffic.databinding.FragmentRelatedPersonBinding
import com.example.android_traffic.membercenter.viewmodel.RelatedPersonViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_traffic.R
import com.example.android_traffic.core.model.RelatedPerson
import com.example.android_traffic.membercenter.adapter.RelatedPersonListAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RelatedPersonFragment : Fragment() {
    private lateinit var binding: FragmentRelatedPersonBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel: RelatedPersonViewModel by viewModels()
        binding = FragmentRelatedPersonBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            activity?.title = "關係人資料"
//          recyclerView一定要設定 LinearLayoutManager 不然不會顯示東西
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            // 從偏好設定中讀取 JSON 字符串
            val sharedPreferences = requireContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
            val savedJsonString = sharedPreferences.getString("relatedPersonJson", "")
            // 將 JSON 字符串轉換為物件
            val gson = Gson()
            val savedRelatedPerson = gson.fromJson(savedJsonString, RelatedPerson::class.java)

            if (savedRelatedPerson != null) {
                viewModel?.relatedPersonList?.add(savedRelatedPerson)
                val editor = sharedPreferences.edit()
                editor.clear()
                editor.apply()
            }

            viewModel?.relatedPerson?.observe(viewLifecycleOwner) { relatedPerson ->
                // adapter為null要建立新的adapter；之後只要呼叫updateFriends(friends)即可
                if (recyclerView.adapter == null) {
                    println("notthing~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
                    recyclerView.adapter = RelatedPersonListAdapter(viewModel?.relatedPersonList as List<RelatedPerson>)
                } else {
                    println("change~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
                    (recyclerView.adapter as RelatedPersonListAdapter).updateRelatedPerson(
                        relatedPerson
                    )
                }
//            }

                //新增關係人的浮動按鈕監聽
                fabMemberCenterRelatedPersonAdd.setOnClickListener {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_relatedPersonFragment_to_relatedPersonAddFragment)
                }
            }
        }
    }

//    override fun onResume() {
//        super.onResume()
//        with(binding){
////            viewModel?.relatedPersonN?.value?.let { viewModel?.relatedPersonList?.add(it) }
//            (recyclerView.adapter as RelatedPersonListAdapter).updateRelatedPerson(
//                viewModel?.relatedPersonList as List<RelatedPerson>
//            )
//        }
//
//    }
}