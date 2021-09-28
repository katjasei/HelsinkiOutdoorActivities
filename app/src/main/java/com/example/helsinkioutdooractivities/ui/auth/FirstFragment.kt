package com.example.helsinkioutdooractivities.ui.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.helsinkioutdooractivities.R

class FirstFragment: Fragment()  {

    //VARIABLES
    private var activityCallBack: FirstFragmentListener? = null

    //FUNCTIONS AND INTERFACES
    interface FirstFragmentListener {
        fun onButtonSignUpClick()
        fun onButtonSignInClick()
    }

    override fun onAttach(context: Context)   {
        super.onAttach(context)
        activityCallBack =  context as FirstFragmentListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        val buttonSignUp = view.findViewById<Button>(R.id.btn_sign_up)
        val buttonSignIn = view.findViewById<Button>(R.id.btn_sign_in)

        //callbacks for buttons in this fragment
        //button Sign Up
        buttonSignUp.setOnClickListener {
            activityCallBack!!.onButtonSignUpClick()
        }
        //button Sign In
        buttonSignIn.setOnClickListener {
            activityCallBack!!.onButtonSignInClick()
        }

        return view
    }


}