package com.example.android_traffic

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android_traffic.databinding.FragmentMainBinding
import com.example.android_traffic.ticket.model.Token

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

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
        }
        saveCreditCard()
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