package com.example.helsinkioutdooractivities.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.helsinkioutdooractivities.R
import com.example.helsinkioutdooractivities.adapters.WorkoutAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CreateWorkoutPlanFragment:  Fragment() {

    private var activityCallBack: CreateWorkoutFragmentListener? = null

    //FUNCTIONS AND INTERFACES
    interface CreateWorkoutFragmentListener {
        fun onButtonStartClick()

    }
    override fun onAttach(context: Context)   {
        super.onAttach(context)
        activityCallBack =  context as CreateWorkoutFragmentListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_createplan, container, false)
        val arrowBackButton = view.findViewById<FloatingActionButton>(R.id.arrow_back_button_create_plan_frag)
        val startButton = view.findViewById<Button>(R.id.start_button)
        arrowBackButton.setOnClickListener {
            //move to MainActivity (HomeActivity)
            val intent = Intent(this.context, MainActivity::class.java)
            // val fragmentString = "FRAGMENT_A"
            intent.putExtra("frgToLoad", 3)
            startActivity(intent)
        }

        startButton.setOnClickListener {
            activityCallBack!!.onButtonStartClick()
        }
        return view
    }
}