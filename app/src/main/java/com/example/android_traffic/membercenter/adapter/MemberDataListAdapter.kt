package com.example.android_traffic.membercenter.adapter

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

import com.example.android_traffic.R
import com.example.android_traffic.databinding.ItemMemberDataBinding
import com.example.android_traffic.core.model.Member
import com.example.android_traffic.membercenter.viewmodel.MemberDataEditViewModel

/**
 * 個人資料所需的Adapter
 */
class MemberDataListAdapter(private var memberData: Member) : //memberData在ViewModel那從後端取得的member資料
    RecyclerView.Adapter<MemberDataListAdapter.MemberDataViewHolder>() {
    private val memberDataTitle = listOf(
        R.string.txt_MemberData_Edit_Avatar,
        R.string.txt_MemberData_Edit_Name,
        R.string.txt_MemberData_Edit_Nickname,
        R.string.txt_MemberData_Edit_IdentityNumber,
        R.string.txt_MemberData_Edit_Birthday,
        R.string.txt_MemberData_Edit_PhoneNo,
        R.string.txt_MemberData_Edit_Email,
        R.string.txt_MemberData_Address,
        R.string.txt_MemberData_Edit_VehideData,
        R.string.txt_MemberData_Edit_Password
    )

    fun updateMemberData(memberData: Member) {
        this.memberData = memberData
        notifyDataSetChanged()
    }

    class MemberDataViewHolder(val itemViewBinding: ItemMemberDataBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return memberDataTitle.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberDataViewHolder {
        val itemViewBinding = ItemMemberDataBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = MemberDataEditViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return MemberDataViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: MemberDataViewHolder, position: Int) {
        val title = memberDataTitle[position]
        with(holder) {
            var tempMember: Member = Member()
            if (position == 0) { //頭像
                itemViewBinding.viewModel?.memberData?.value = memberData.avatar
                itemViewBinding.viewModel?.memberData?.value = null
            } else if (position == 5){ //手機
                itemViewBinding.ivMemberCenterRight.visibility = View.INVISIBLE
                itemViewBinding.viewModel?.memberData?.value = memberData.phoneNo
            }else { //頭像以外
                when (position) {  //8車輛資料 9修改密碼
                    1 -> {
                        itemViewBinding.viewModel?.memberData?.value = memberData.name
                        tempMember.name = memberData.name
                    }
                    2 -> {
                        itemViewBinding.viewModel?.memberData?.value = memberData.nickname
                        tempMember.nickname = memberData.nickname
                    }
                    3 -> {
                        itemViewBinding.viewModel?.memberData?.value = memberData.identityNumber
                        tempMember.identityNumber = memberData.identityNumber
                    }
                    4 -> {
                        itemViewBinding.viewModel?.memberData?.value = memberData.birthday
                        tempMember.birthday = memberData.birthday
                    }
                    6 -> {
                        itemViewBinding.viewModel?.memberData?.value = memberData.email
                        tempMember.email = memberData.email
                    }
                    7 -> {
                        itemViewBinding.viewModel?.memberData?.value = memberData.address
                        tempMember.address = memberData.address
                    }
                    9 -> {
                        tempMember.password = memberData.password
                    }
                    else -> null
                }
            }
            if (position == 9){
                itemViewBinding.viewModel?.title?.value =
                    itemViewBinding.root.context.resources.getString(R.string.txt_MemberData_Bt_Edit) +
                    itemViewBinding.root.context.resources.getString(title)
            } else {
                itemViewBinding.viewModel?.title?.value =
                    itemViewBinding.root.context.resources.getString(title)
            }

            val bundle = Bundle()
            if (position == 8) {    //如果是8車輛資料 改進車輛資料頁 8以外的進修改資料
                bundle.putSerializable("memberID", memberData.id)
                itemView.setOnClickListener {
                    Navigation.findNavController(it)
                        .navigate(
                            R.id.action_memberDataFragment_to_memberDataVehideDataFragment, bundle
                        )
                }
            } else if (position == 5) { //取消手機的監聽
                itemView.setOnClickListener(null)
            } else if (position == 0) { //上傳頭像的監聽
                itemView.setOnClickListener {
                    alertDialogPicture(itemView.context)
                }
            }
            else {
                bundle.putSerializable("memberDataTitle", title)
                bundle.putSerializable("memberData", tempMember)
                itemView.setOnClickListener {
                    Navigation.findNavController(it)
                        .navigate(
                            R.id.action_memberDataFragment_to_memberDataEditFragment, bundle
                        )
                }
            }
        }
    }

    fun alertDialogPicture(context: Context) {

        val dialogView = LayoutInflater.from(context).inflate(R.layout.membercent_memeberdata_picture_alertdialog, null)
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setView(dialogView)
        val alertDialog = alertDialogBuilder.create()

        // 自訂布局李的按鈕監聽
        val takePictureButton = dialogView.findViewById<TextView>(R.id.tv_MemberData_TakePictrue)
        val pickPictureButton = dialogView.findViewById<TextView>(R.id.tv_MemberData_PickPictrue)
        takePictureButton.setOnClickListener {//拍照按鈕的監聽
            // 按下拍照按鈕的監聽事件
            println("ㄆ拍")
            // 在這裡處理拍照的操作
            alertDialog.dismiss()
        }
        pickPictureButton.setOnClickListener {//相簿按鈕的監聽
            // 按下挑選圖片按鈕的監聽事件
            // 在這裡處理挑選圖片的操作
            println("挑挑")
            alertDialog.dismiss()
        }
        alertDialog.show()
    }
}
