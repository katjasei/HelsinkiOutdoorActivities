package com.example.helsinkioutdooractivities.ui.auth

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.helsinkioutdooractivities.R

class AuthActivity:  AppCompatActivity(), FirstFragment.FirstFragmentListener, LoginFragment.LoginFragmentListener {

    //Create a new Fragment to be placed in the activity layout
    private val firstFragment = FirstFragment()
    private val loginFragment = LoginFragment()
    private val registrationFragment = RegistrationFragment()
    private val splashScreenFragment = SplashScreenFragment()
    private val permissionFragment = PermissionFragment()

    //FUNCTIONS:
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        replaceFragment(splashScreenFragment)
        val handler = Handler()
        handler.postDelayed({
            run {
                //go to FirstFragment, if user not logged in
                replaceFragment(firstFragment)
            }
        },3000)

        hideSystemUI()
    }

    //FirstFragment listeners:
    //when button "sign up" clicked from FirstFragment
    override fun onButtonSignUpClick() {
        replaceFragment(registrationFragment)
    }
    //when button "sign in" clicked from FirstFragment
    override fun onButtonSignInClick() {
        replaceFragment(loginFragment)
    }

    //when button "log in" clicked from FirstFragment
    override fun onButtonLogInClick() {
        replaceFragment(permissionFragment)
    }

    //function used for fragment replacement
    private fun replaceFragment(fragment: Fragment){
        //fragment manager can help when switching to the other fragment is needed
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun hideSystemUI() {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
}