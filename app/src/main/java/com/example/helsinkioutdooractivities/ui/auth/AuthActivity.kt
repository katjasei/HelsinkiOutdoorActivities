package com.example.helsinkioutdooractivities.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.helsinkioutdooractivities.R

class AuthActivity:  AppCompatActivity(), FirstFragment.FirstFragmentListener {

    //Create a new Fragment to be placed in the activity layout
    private val firstFragment = FirstFragment()
    private val loginFragment = LoginFragment()
    private val registrationFragment = RegistrationFragment()

    //FUNCTIONS:
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

    }

    //FirstFragment listeners:
    //when button "sign up" clicked from FirstFragment
    override fun onButtonSignUpClick() {
        //replaceFragment(registrationFragment)
    }
    //when button "sign in" clicked from FirstFragment
    override fun onButtonSignInClick() {
        //replaceFragment(loginFragment)
    }

    //function used for fragment replacement
    private fun replaceFragment(fragment: Fragment){
        // fragment manager can help when switching to the other fragment is needed
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}