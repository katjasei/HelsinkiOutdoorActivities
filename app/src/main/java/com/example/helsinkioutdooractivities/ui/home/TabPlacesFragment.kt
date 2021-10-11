package com.example.helsinkioutdooractivities.ui.home

import android.content.Context
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.helsinkioutdooractivities.R

class TabPlacesFragment: Fragment() {

    //VARIABLES
    private var activityCallBack: TabPlacesFragmentListener? = null

    //FUNCTIONS AND INTERFACES
    interface TabPlacesFragmentListener {
        fun onClickableImageClick()
    }

    override fun onAttach(context: Context)   {
        super.onAttach(context)
        activityCallBack =  context as TabPlacesFragmentListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_places, container, false)
        val clickableImage = view.findViewById<ImageView>(R.id.clickable_image)

        clickableImage.setOnClickListener {
            activityCallBack!!.onClickableImageClick()
        }
        return view
    }
}