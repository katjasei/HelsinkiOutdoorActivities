package com.example.helsinkioutdooractivities.utils

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.helsinkioutdooractivities.data.model.ClassUser
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

//VARIABLES:
//firebase db
var mFirebaseDB = FirebaseDatabase.getInstance().reference

//function add user to Firebase DB
fun addUserNameToUser(userFromRegistration: FirebaseUser, textView: TextView){
    val username = textView.text.toString()
    val email = userFromRegistration.email
    val userId = userFromRegistration.uid
    val user = ClassUser(username, email!!)
    mFirebaseDB.child("users")
        .child(userId)
        .setValue(user)
}

fun checkIfParameterExistInFirebaseDB (parameter:String, parameterName:String, textView: TextView){
    mFirebaseDB.child("users")
        .orderByChild(parameterName)
        .equalTo(parameter)
        .addListenerForSingleValueEvent(object: ValueEventListener {
            @SuppressLint("SetTextI18n")
            override fun onDataChange(p0: DataSnapshot) {
                Log.d("p0",p0.toString())
                if (p0.exists()){
                    textView.visibility = View.VISIBLE
                    textView.text = "$parameter already in use"
                } else {
                    textView.text = ""
                }
            }
            override fun onCancelled(p0: DatabaseError) {
            }
        })
}