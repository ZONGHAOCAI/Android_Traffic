package com.example.android_traffic.ticket.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.android_traffic.R
import com.example.android_traffic.databinding.FragmentTicketAppealBinding
import com.example.android_traffic.ticket.model.Content
import com.example.android_traffic.ticket.viewmodel.TicketAppealContentViewModel
import com.example.android_traffic.ticket.viewmodel.TicketContentViewModel

class TicketAppealAdapter (private var appealcontent: List<Content>) :
    RecyclerView.Adapter<TicketAppealAdapter.Holder>() {
    fun updateTicketAppealList(content: List<Content>) {
        this.appealcontent = content
        notifyDataSetChanged()
    }

    class Holder(val TicketAppealFragmentBinding: FragmentTicketAppealBinding) :
        RecyclerView.ViewHolder(TicketAppealFragmentBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val TicketAppealFragmentBinding =
            FragmentTicketAppealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        TicketAppealFragmentBinding.viewmodel = TicketAppealContentViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        TicketAppealFragmentBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return Holder(TicketAppealFragmentBinding)
    }

    override fun getItemCount(): Int {
        return appealcontent.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        with(holder) {
            TicketAppealFragmentBinding.viewmodel?.content?.value = appealcontent[position]
            val bundle = Bundle()
            bundle.putSerializable("number", appealcontent[position])
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_ticketFragment_to_ticketAppealContentFragment, bundle)
//                    .navigate(R.id.action_TicketAppealListFragment_to_ticketContentFragment, bundle)
            }
        }
    }
}
