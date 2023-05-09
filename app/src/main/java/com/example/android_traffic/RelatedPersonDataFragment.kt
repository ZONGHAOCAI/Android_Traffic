package com.example.android_traffic

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class RelatedPersonDataFragment : Fragment() {

    companion object {
        fun newInstance() = RelatedPersonDataFragment()
    }

    private lateinit var viewModel: RelatedPersonDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_related_person_data, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RelatedPersonDataViewModel::class.java)
        // TODO: Use the ViewModel
    }

}