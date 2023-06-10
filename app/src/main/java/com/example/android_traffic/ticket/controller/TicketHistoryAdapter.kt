package com.example.android_traffic.ticket.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.android_traffic.R
import com.example.android_traffic.databinding.FragmentTicketHistoryBinding
import com.example.android_traffic.ticket.model.Content
import com.example.android_traffic.ticket.viewmodel.TicketHistoryContentViewModel

class TicketHistoryAdapter (private var historycontent: List<Content>) :
    RecyclerView.Adapter<TicketHistoryAdapter.Holder>() {
    fun updateTicketHistoryList(content: List<Content>) {
        this.historycontent = content
        notifyDataSetChanged()
    }

    class Holder(val TicketHistoryFragmentBinding: FragmentTicketHistoryBinding) :
        RecyclerView.ViewHolder(TicketHistoryFragmentBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val TicketHistoryFragmentBinding =
            FragmentTicketHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        TicketHistoryFragmentBinding.viewmodel = TicketHistoryContentViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        TicketHistoryFragmentBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return Holder(TicketHistoryFragmentBinding)
    }

    override fun getItemCount(): Int {
        return historycontent.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        with(holder) {
            TicketHistoryFragmentBinding.viewmodel?.content?.value = historycontent[position]
            val bundle = Bundle()
            bundle.putSerializable("number", historycontent[position])
            itemView.setOnClickListener {
                Navigation.findNavController(it)
//                    .navigate(R.id.action_ticketFragment_to_ticketAppealListFragment, bundle)
                    .navigate(R.id.action_ticketFragment_to_ticketHistoryContentFragment, bundle)
            }
        }
    }
}
