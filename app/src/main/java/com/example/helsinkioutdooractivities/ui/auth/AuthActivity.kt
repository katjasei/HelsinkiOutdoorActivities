package com.example.helsinkioutdooractivities.ui.auth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.helsinkioutdooractivities.R
import com.example.helsinkioutdooractivities.ui.home.MainActivity
import com.example.helsinkioutdooractivities.utils.hideSystemUI
import com.example.helsinkioutdooractivities.utils.replaceFragment
import com.example.helsinkioutdooractivities.viewModel.LoginViewModel

class AuthActivity:  AppCompatActivity(), FirstFragment.FirstFragmentListener,
RegistrationFragment.RegistrationFragmentListener, LoginFragment.LoginFragmentListener{

    //Create a new Fragment to be placed in the activity layout
    private val firstFragment = FirstFragment()
    private val loginFragment = LoginFragment()
    private val registrationFragment = RegistrationFragment()
    private val splashScreenFragment = SplashScreenFragment()
    private val permissionFragment = PermissionFragment()
    private val loginViewModel = LoginViewModel()
    // bundle needs for communication between two fragments
    private val bundle = Bundle()

    //FUNCTIONS:
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        replaceFragment(splashScreenFragment, supportFragmentManager)
        observeAuthenticationState()
        hideSystemUI(window)
    }

    //function check if user already logged in or not (observer)
    private fun observeAuthenticationState() {
        loginViewModel.authenticationState.observe(this, Observer {
            when (it) {
                LoginViewModel.AuthenticationState.AUTHENTICATED -> {
                    val handler = Handler(Looper.getMainLooper())
                    handler.postDelayed({
                        run {
                            //go to PermissionFragment, if logged in
                            replaceFragment(permissionFragment, supportFragmentManager)
                        }
                    },3000)
                }
                else -> {

                    val handler = Handler(Looper.getMainLooper())
                    handler.postDelayed({
                        run {
                            //go to FirstFragment, if user not logged in
                            replaceFragment(firstFragment, supportFragmentManager)
                        }
                    },3000)
            }
            }
        })
    }

    //FirstFragment listeners:
    //when button "sign up" clicked from FirstFragment
    override fun onButtonSignUpClick() {
        replaceFragment(registrationFragment, supportFragmentManager)
    }
    //when button "sign in" clicked from FirstFragment
    override fun onButtonSignInClick() {
        replaceFragment(loginFragment, supportFragmentManager)
    }

    override fun onButtonSignUpClickFromRegistration(username: String) {
       // replaceFragment(permissionFragment, supportFragmentManager)
        //do I need it???? Bundle
        //bundle.putCharSequence("username" , username)
       // welcomeFragment.arguments = bundle
    }

    override fun onArrowBackButtonClicked() {
        replaceFragment(FirstFragment(), supportFragmentManager)
    }

    override fun onButtonArrowBackClicked() {
        replaceFragment(FirstFragment(), supportFragmentManager)
    }

    override fun onButtonLogInClicked() {
        replaceFragment(permissionFragment, supportFragmentManager)
    }


}