package com.example.helsinkioutdooractivities.ui.place

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.helsinkioutdooractivities.R
import com.example.helsinkioutdooractivities.utils.hideSystemUI
import com.example.helsinkioutdooractivities.utils.replaceFragment

class PlaceActivity : AppCompatActivity(){

    //VARIABLES:
    private val gymInformationFragment = GymInformationFragment()

    //FUNCTIONS:
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place)
        replaceFragment(gymInformationFragment, supportFragmentManager)
        hideSystemUI(window)
    }

}