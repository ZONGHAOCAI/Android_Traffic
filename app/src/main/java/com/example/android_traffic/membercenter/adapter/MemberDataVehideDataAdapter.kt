package com.example.android_traffic.membercenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.android_traffic.core.model.Vehide
import com.example.android_traffic.databinding.ItemMemberVehidedataBinding
import com.example.android_traffic.membercenter.viewmodel.MemberDataVehideDataViewModel

/**
 * 好友列表所需的Adapter
 */
class MemberDataVehideDataAdapter(private var vehideDataList: List<Vehide>) :
    RecyclerView.Adapter<MemberDataVehideDataAdapter.VehideViewHolder>() {

    class VehideViewHolder(val itemViewBinding: ItemMemberVehidedataBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return vehideDataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehideViewHolder {
        val itemViewBinding = ItemMemberVehidedataBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = MemberDataVehideDataViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return VehideViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: VehideViewHolder, position: Int) {
        val vehide = vehideDataList[position]
        with(holder) {
            itemViewBinding.viewModel?.vehideType?.value = vehide.vehideType
            itemViewBinding.viewModel?.vehideData?.value = vehide.vehideNumber
        }
    }
}