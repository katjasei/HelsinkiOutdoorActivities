package com.example.helsinkioutdooractivities.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.helsinkioutdooractivities.R
import com.example.helsinkioutdooractivities.ui.home.MainActivity

class LoginFragment: Fragment() {

    //VARIABLES
    private var activityCallBack: LoginFragmentListener? = null

    //FUNCTIONS AND INTERFACES
    interface LoginFragmentListener {
        fun onButtonLogInClick()
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
           activityCallBack!!.onButtonLogInClick()
            //function login
           // userLogin(valueEmail.text.toString(), valuePassword.text.toString())


/*            activity!!.finish()
            //move to MainActivity (HomeActivity)
            val intent = Intent(this.context, MainActivity::class.java)
            startActivity(intent)*/
        }
        return view
    }




}