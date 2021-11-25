package com.example.helsinkioutdooractivities.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helsinkioutdooractivities.adapters.ExerciseAdapter
import com.example.helsinkioutdooractivities.data.model.ClassUser
import com.example.helsinkioutdooractivities.data.model.ExerciseItem
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

//VARIABLES:
//firebase db
var mFirebaseDB = FirebaseDatabase.getInstance("https://helsinkioutdoorexersices-default-rtdb.europe-west1.firebasedatabase.app").reference
var exerciseItems: MutableList<ExerciseItem> = java.util.ArrayList()

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

// function for getting user name from database
fun getUserNameFromDataBase(userID:String, textView:TextView){

    mFirebaseDB.child("users")
        .child(userID)
        .addValueEventListener(object: ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                val username = p0.child("username").value.toString()
                textView.text = username
            }
            override fun onCancelled(p0: DatabaseError) {
                // Failed to read value
                Log.d("Failed to read value.", "")
            }
        }
        )
}

fun getExercisesListInfoFromDB(recyclerViewExercises: RecyclerView, context: Context) {
    mFirebaseDB.child("exercises")
        .addValueEventListener(object: ValueEventListener {
            @SuppressLint("SetTextI18n")
            override fun onDataChange(p0: DataSnapshot) {
                exerciseItems.clear()
                val children = p0.children
                children.forEach {
                    //println("instruction " + it.child("instructions").value.toString())
                    exerciseItems.add(ExerciseItem(it.child("exercise_name").value.toString(),
                        it.child("instructions").value.toString(),
                        it.child("area_of_focus").value.toString(),
                        it.child("exercise_picture").value.toString()))
                }
                exerciseItems.forEach{
                    println(it.area_of_focus)
                }
                val adapter2 = ExerciseAdapter(
                    context,
                    exerciseItems
                )
                recyclerViewExercises.adapter = adapter2
                val gridLayoutManager = GridLayoutManager(context, 2)
                recyclerViewExercises.layoutManager = gridLayoutManager

            }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
}