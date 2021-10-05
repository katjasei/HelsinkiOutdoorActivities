package com.example.helsinkioutdooractivities.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.helsinkioutdooractivities.R
import com.example.helsinkioutdooractivities.ui.home.MainActivity


class RegistrationFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_registration, container, false)
        val buttonSignUp = view.findViewById<Button>(R.id.btn_sign_up)
        // after button sign up is clicked
        buttonSignUp.setOnClickListener {
            activity!!.finish()
            //move to MainActivity (HomeActivity)
            val intent = Intent(this.context, MainActivity::class.java)
            startActivity(intent)
        }
        return view
    }

    }