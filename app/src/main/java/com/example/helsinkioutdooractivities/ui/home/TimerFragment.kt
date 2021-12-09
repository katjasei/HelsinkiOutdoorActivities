package com.example.helsinkioutdooractivities.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.helsinkioutdooractivities.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TimerFragment:  Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_timer, container, false)
        val arrowBackButton = view.findViewById<FloatingActionButton>(R.id.arrow_back_button_timer)
        arrowBackButton.setOnClickListener {
            //move to MainActivity (HomeActivity)
            val intent = Intent(this.context, MainActivity::class.java)
            // val fragmentString = "FRAGMENT_A"
            intent.putExtra("frgToLoad", 4)
            startActivity(intent)
        }
        return view
    }
}