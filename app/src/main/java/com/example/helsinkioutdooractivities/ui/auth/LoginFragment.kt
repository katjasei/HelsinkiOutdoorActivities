package com.example.helsinkioutdooractivities.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.helsinkioutdooractivities.R

class LoginFragment: Fragment() {



    //FUNCTIONS:
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_login, container, false)
        val buttonLogin = view.findViewById<Button>(R.id.btn_sign_in)
        val valueEmail = view.findViewById<TextView>(R.id.value_email)
        val valuePassword = view.findViewById<TextView>(R.id.value_password)

        // after button sign in is clicked
        buttonLogin.setOnClickListener {
            //function login
           // userLogin(valueEmail.text.toString(), valuePassword.text.toString())
        }
        return view
    }


}