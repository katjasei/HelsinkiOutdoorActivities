package com.example.helsinkioutdooractivities.ui.exercise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.helsinkioutdooractivities.R
import com.example.helsinkioutdooractivities.ui.home.AddExerciseFragment
import com.example.helsinkioutdooractivities.utils.hideSystemUI
import com.example.helsinkioutdooractivities.utils.replaceFragment

class ExerciseActivity : AppCompatActivity(){

    //VARIABLES:
    private val exerciseFragment = ExerciseFragment()
    val bundle = Bundle()

    //FUNCTIONS:
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        hideSystemUI(window)

        bundle.putString("ExercisePicture", intent.extras?.get("ExercisePicture") as String?)
        bundle.putString("ExerciseName", intent.extras?.get("ExerciseName") as String?)
        bundle.putString("Instructions", intent.extras?.get("Instructions") as String)
        bundle.putString("AreaOfFocus", intent.extras?.get("AreaOfFocus") as String)

        exerciseFragment.arguments = bundle

        replaceFragment(exerciseFragment, supportFragmentManager)
        // println(intent.extras?.get("Address"))
    }

}