package com.example.android_traffic.membercenter.controller

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_traffic.R
import com.example.android_traffic.membercenter.viewmodel.RelatedPersonDataEditViewModel

class RelatedPersonDataEditFragment : Fragment() {

    companion object {
        fun newInstance() = RelatedPersonDataEditFragment()
    }

    private lateinit var viewModel: RelatedPersonDataEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_related_person_data_edit, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RelatedPersonDataEditViewModel::class.java)
        // TODO: Use the ViewModel
    }

}