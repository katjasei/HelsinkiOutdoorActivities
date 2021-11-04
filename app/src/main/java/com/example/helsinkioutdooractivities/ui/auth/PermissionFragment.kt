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

class PermissionFragment:  Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_permission, container, false)
        val buttonPermission = view.findViewById<Button>(R.id.btn_allow_services)
        buttonPermission.setOnClickListener {
            //function login
            // userLogin(valueEmail.text.toString(), valuePassword.text.toString())
              requireActivity().finish()
             //move to MainActivity (HomeActivity)
             val intent = Intent(this.context, MainActivity::class.java)
             intent.putExtra("frgToLoad", 0)
             startActivity(intent)
        }
        return view
    }
}