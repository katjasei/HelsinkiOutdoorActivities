package com.example.helsinkioutdooractivities.ui.auth

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.helsinkioutdooractivities.R
import com.example.helsinkioutdooractivities.ui.home.MainActivity
import com.example.helsinkioutdooractivities.utils.PreferenceHelper
import com.example.helsinkioutdooractivities.utils.PreferenceHelper.password
import com.example.helsinkioutdooractivities.utils.PreferenceHelper.userEmail
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.value_email
import kotlinx.android.synthetic.main.fragment_login.value_password
import kotlinx.android.synthetic.main.fragment_registration.*

class LoginFragment: Fragment() {

    //VARIABLES:
    //firebase auth object
    private var mFirebaseAuth = FirebaseAuth.getInstance()
    //callback variable, interface and onAttach fun
    private var activityCallBack: LoginFragment.LoginFragmentListener? = null

    //INTERFACES AND FUNCTIONS:
    interface LoginFragmentListener {
        fun onButtonLogInClicked()
    }
    override fun onAttach(context: Context)   {
        super.onAttach(context)
        activityCallBack =  context as LoginFragmentListener
    }


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
            userLogin(valueEmail.text.toString(), valuePassword.text.toString())
        }

        //using the preferences from PreferenceHelper
        val prefs = PreferenceHelper.customPreference(requireContext(), "prefs")
        //get user email and password from Shared Preferences
        valueEmail.text = prefs.userEmail
        valuePassword.text = prefs.password

        return view
    }

    @SuppressLint("SetTextI18n")
    // user login through Firebase
    private fun userLogin(email:String, password:String){
        //check if email and password fields are not empty
        if (value_email.text.toString().isNotEmpty() && value_password.text.toString().isNotEmpty()) {
            //logging in the user
            mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                     //   activityCallBack!!.onButtonLogInClicked()
                    } else {
                        txt_login_fail.visibility = View.VISIBLE
                        txt_login_fail.text = "Email or password is incorrect"
                    }
                }
        } else {
            //check if user email and password are entered
            if (value_email.text.toString().isEmpty()){
                Toast.makeText(this.context,"Please enter email", Toast.LENGTH_LONG).show()
            }
            if(value_password.text.toString().isEmpty()){
                Toast.makeText(this.context,"Please enter password", Toast.LENGTH_LONG).show()
            }
        }
    }




}