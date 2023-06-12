package com.example.android_traffic.ticket.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.android_traffic.R
import com.example.android_traffic.databinding.FragmentTicketBinding
import com.example.android_traffic.ticket.model.Page
import com.example.android_traffic.ticket.viewmodel.TicketViewModel
import com.google.android.material.tabs.TabLayoutMediator

class TicketFragment : Fragment() {
    private lateinit var binding: FragmentTicketBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewmodel: TicketViewModel by viewModels()
        binding = FragmentTicketBinding.inflate(inflater, container, false)
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.title = "罰單列表"
        handleViews()
    }

    private fun handleViews() {
        val pages = listOf(
            Page("未繳費", TicketUnpaidListFragment()),
            Page("申訴中", TicketAppealListFragment()),
            Page("歷史紀錄", TicketHistoryListFragment())

        )

        with(binding) {
            ViewPager2.adapter = MyFragmentStateAdapter(this@TicketFragment, pages)
            TabLayoutMediator(tabLayout, ViewPager2) { tab, position ->
                // 設定tab標題文字
                tab.text = pages[position].title
            }.attach()
        }
    }

    class MyFragmentStateAdapter(fragment: TicketFragment, private var pages: List<Page>) :
        FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int {
            return pages.size
        }

        override fun createFragment(position: Int): Fragment {
            return pages[position].fragment
        }
    }
}