package com.example.helsinkioutdooractivities.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.helsinkioutdooractivities.R
import com.example.helsinkioutdooractivities.data.model.ClassLocation
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_gym_informatin.*
import kotlinx.android.synthetic.main.fragment_popup.*

class PopUpWindowDialog  : DialogFragment()  {


    //callback variable, interface and onAttach fun
    private var activityCallBack: DialogFragmentListener? = null

    //INTERFACES AND FUNCTIONS:
    interface DialogFragmentListener {
        fun onButtonMoreInfoClicked(address : String, distance : String, pictureUrl : String, name : String)
    }
    override fun onAttach(context: Context)   {
        super.onAttach(context)
        activityCallBack =  context as DialogFragmentListener
    }
    companion object {

        const val TAG = "PopUpWindowDialog"

        fun newInstance(address : String, distance: String, picture: String, name : String): PopUpWindowDialog {
            val args = Bundle()
            args.putString("AddressPopUp", address)
            args.putString("DistancePopUp", distance)
            args.putString("PicturePopUp", picture)
            args.putString("NamePopUp", name)
            val fragment = PopUpWindowDialog()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val arguments = arguments
        val view = inflater.inflate(R.layout.fragment_popup, container, false)
        val addressPopUp = view.findViewById<TextView>(R.id.gym_address_pop_up)
        val distancePopUp = view.findViewById<TextView>(R.id.distance_pop_up)
        val picturePopUp = view.findViewById<ImageView>(R.id.gym_image_pop_up)
        val namePopUp = view.findViewById<TextView>(R.id.gym_name_pop_up)
        val btnMoreInfo = view.findViewById<Button>(R.id.btn_more_info)
        var address = ""
        var distance = ""
        var name = ""
        var pictureUrl = ""
        if(arguments != null) {
            address = arguments.getString("AddressPopUp").toString()
            distance =  arguments.getString("DistancePopUp").toString()
            pictureUrl = arguments.getString("PicturePopUp" ).toString()
            name = arguments.getString("NamePopUp").toString()

           addressPopUp.text = address
           distancePopUp.text = distance
           if(pictureUrl != ""){
               Picasso.get().load(pictureUrl).into(picturePopUp)
           }
           namePopUp.text = name
        }

        btnMoreInfo.setOnClickListener {
            activityCallBack!!.onButtonMoreInfoClicked(address, distance, pictureUrl, name)
        }

        return view
    }

}