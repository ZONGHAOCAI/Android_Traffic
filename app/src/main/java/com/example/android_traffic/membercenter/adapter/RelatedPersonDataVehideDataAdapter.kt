package com.example.android_traffic.membercenter.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.android_traffic.core.model.Vehide
import com.example.android_traffic.databinding.ItemRelatedpersonVehidedataBinding
import com.example.android_traffic.membercenter.viewmodel.RelatedPersonDataVehideDataViewModel


/**
 * 好友列表所需的Adapter
 */
class RelatedPersonDataVehideDataAdapter(private var vehideDataList: List<Vehide>) :
    RecyclerView.Adapter<RelatedPersonDataVehideDataAdapter.VehideViewHolder>() {

    class VehideViewHolder(val itemViewBinding: ItemRelatedpersonVehidedataBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return vehideDataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehideViewHolder {
        val itemViewBinding = ItemRelatedpersonVehidedataBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = RelatedPersonDataVehideDataViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
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