package com.example.helsinkioutdooractivities.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.helsinkioutdooractivities.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SearchFragment : Fragment() {

    //VARIABLES
    private var activityCallBack: SearchFragmentListener? = null

    //FUNCTIONS AND INTERFACES
    interface SearchFragmentListener {
        fun onButtonBackArrowClick()
    }

    override fun onAttach(context: Context)   {
        super.onAttach(context)
        activityCallBack =  context as SearchFragmentListener
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        val backButton = view.findViewById<FloatingActionButton>(R.id.arrow_back_button)
        backButton.setOnClickListener {
            activityCallBack!!.onButtonBackArrowClick()
        }
        return view
    }
}