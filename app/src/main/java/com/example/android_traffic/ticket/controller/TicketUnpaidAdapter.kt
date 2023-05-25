package com.example.android_traffic.ticket.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.android_traffic.R
import com.example.android_traffic.databinding.FragmentTicketUnpaidBinding
import com.example.android_traffic.ticket.model.Content
import com.example.android_traffic.ticket.viewmodel.TicketContentViewModel

class TicketUnpaidAdapter (private var unpaidcontent: List<Content>) :
    RecyclerView.Adapter<TicketUnpaidAdapter.Holder>() {
    fun updateTicketUnpaidList(content: List<Content>) {
        this.unpaidcontent = content
        notifyDataSetChanged()
    }

    class Holder(val ticketFragmentBinding: FragmentTicketUnpaidBinding) :
        RecyclerView.ViewHolder(ticketFragmentBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val TicketUnpaidFragmentBinding =
            FragmentTicketUnpaidBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        TicketUnpaidFragmentBinding.viewmodel = TicketContentViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        TicketUnpaidFragmentBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return Holder(TicketUnpaidFragmentBinding)
    }

    override fun getItemCount(): Int {
        return unpaidcontent.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        with(holder) {
            ticketFragmentBinding.viewmodel?.content?.value = unpaidcontent[position]
            val bundle = Bundle()
            bundle.putSerializable("number", unpaidcontent[position])
            itemView.setOnClickListener {
                Navigation.findNavController(it)
//                    .navigate(R.id.action_ticketListFragment_to_ticketContentFragment, bundle)
                    .navigate(R.id.action_ticketFragment_to_ticketContentFragment, bundle)
            }
        }
    }
}
