package com.example.android_traffic

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class RelatedPersonDataVehideDataFragment : Fragment() {

    companion object {
        fun newInstance() = RelatedPersonDataVehideDataFragment()
    }

    private lateinit var viewModel: RelatedPersonDataVehideDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_related_person_data_vehide_data, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RelatedPersonDataVehideDataViewModel::class.java)
        // TODO: Use the ViewModel
    }

}