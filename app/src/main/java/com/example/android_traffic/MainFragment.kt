package com.example.android_traffic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.android_traffic.databinding.FragmentMainBinding

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
            imgBtnMainTicket.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_mainFragment_to_ticketFragment)
            }
            imgBtnMainChat.setOnClickListener {
                Navigation.findNavController(it)
                .navigate(R.id.action_mainFragment_to_chatRoomFragment) }
        }
    }
}