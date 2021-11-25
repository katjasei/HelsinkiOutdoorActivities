package com.example.helsinkioutdooractivities.ui.exercise

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.helsinkioutdooractivities.R
import com.example.helsinkioutdooractivities.adapters.ExerciseAdapter
import com.example.helsinkioutdooractivities.ui.home.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_exercise.*
import kotlinx.android.synthetic.main.item_exercise.view.*
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class ExerciseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_exercise, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val arguments = arguments
        if(arguments != null) {

            Picasso.get().load(arguments.getString("ExercisePicture")) .into(exercise_picture)
            exercise_name.text = arguments.getString("ExerciseName")
            area_of_focus_text.text = arguments.getString("AreaOfFocus")
            instruction_text.text = arguments.getString("Instructions")
        }

        arrow_back_button_exercise.setOnClickListener {
            val intent = Intent(this.context, MainActivity::class.java)
            intent.putExtra("frgToLoad", 3)
            startActivity(intent)
        }

    }
}