package com.example.android_traffic.ticketappealtext.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.android_traffic.R
import com.example.android_traffic.databinding.FragmentTicketAppealtext3Binding
import com.example.android_traffic.ticketappealtext.viewmodel.TicketAppealtext3ViewModel

class TicketAppealtext3Fragment : Fragment() {


    private lateinit var binding: FragmentTicketAppealtext3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel:TicketAppealtext3ViewModel by viewModels()
        binding= FragmentTicketAppealtext3Binding.inflate(inflater,container,false)
        binding.viewModel=viewModel
        binding.lifecycleOwner=this
        return binding.root
    }
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    with(binding){
        val menu = binding.tttmenu2
        val select=arguments?.getString("select")
        menu.text=select
arguments?.let {
    viewModel?.con2?.value=it.getString("con")
    viewModel?.contwo2?.value=it.getString("contwo")

}

 gonext2.setOnClickListener {
     if (tttconfirm2.isChecked){
         Navigation.findNavController(it).navigate(R.id.ticketAppealtext4Fragment)
         ttterrocon.text=""
     }else{
         val errorMessage ="確認資料無誤後,請點選確認並送出"
         ttterrocon.text = errorMessage
     }
 }
goback2.setOnClickListener {
    findNavController().popBackStack()
}
    }

}

}







