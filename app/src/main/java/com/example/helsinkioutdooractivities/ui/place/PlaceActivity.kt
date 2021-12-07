package com.example.helsinkioutdooractivities.ui.place

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.helsinkioutdooractivities.R
import com.example.helsinkioutdooractivities.adapters.GymsAdapter
import com.example.helsinkioutdooractivities.data.model.GymListItem
import com.example.helsinkioutdooractivities.ui.home.TabPlacesFragment
import com.example.helsinkioutdooractivities.utils.hideSystemUI
import com.example.helsinkioutdooractivities.utils.replaceFragment

class PlaceActivity : AppCompatActivity(){

    //VARIABLES:
    private val gymInformationFragment = GymInformationFragment()
    val bundle = Bundle()

    //FUNCTIONS:
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place)

        hideSystemUI(window)

        bundle.putString("Address", intent.extras?.get("Address") as String?)
        bundle.putString("GymImage", intent.extras?.get("GymImage") as String)
        bundle.putString("Distance", intent.extras?.get("Distance") as String)

        gymInformationFragment.arguments = bundle

        replaceFragment(gymInformationFragment, supportFragmentManager)

    }

}