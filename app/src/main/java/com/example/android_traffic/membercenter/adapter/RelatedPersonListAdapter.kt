package com.example.android_traffic.membercenter.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

import com.example.android_traffic.R
import com.example.android_traffic.databinding.ItemRelatedPersonBinding
import com.example.android_traffic.membercenter.model.RelatedPerson
import com.example.android_traffic.membercenter.viewmodel.RelatedPersonDataViewModel

/**
 * 關係人列表所需的Adapter
 */
class RelatedPersonListAdapter(private var relatedPerson: List<RelatedPerson>) :
    RecyclerView.Adapter<RelatedPersonListAdapter.RelatedPersonViewHolder>() {

    /**
     * 更新好友列表內容
     * @param relatedPerson 新的關係人列表
     */
    fun updateRelatedPerson(relatedPerson: List<RelatedPerson>) {
        this.relatedPerson = relatedPerson
        notifyDataSetChanged()
    }

    class RelatedPersonViewHolder(val itemViewBinding: ItemRelatedPersonBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return relatedPerson.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RelatedPersonViewHolder {
        val itemViewBinding = ItemRelatedPersonBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = RelatedPersonDataViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return RelatedPersonViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: RelatedPersonViewHolder, position: Int) {
        val relatedPerson = relatedPerson[position]
        with(holder) {
            // 將欲顯示的關係人物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemViewBinding.viewModel?.relatedPerson?.value = relatedPerson
            val bundle = Bundle()
            bundle.putSerializable("relatedPerson", relatedPerson)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(
                        R.id.action_relatedPersonFragment_to_relatedPersonDataFragment, bundle)
            }
        }
    }
}
