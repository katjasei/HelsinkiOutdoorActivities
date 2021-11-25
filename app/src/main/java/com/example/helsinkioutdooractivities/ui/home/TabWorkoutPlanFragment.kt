package com.example.helsinkioutdooractivities.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helsinkioutdooractivities.R
import com.example.helsinkioutdooractivities.adapters.WorkoutPlanAdapter
import com.example.helsinkioutdooractivities.data.model.ClassNumberOfExercises
import com.example.helsinkioutdooractivities.utils.getExercisesListInfoFromDB

class TabWorkoutPlanFragment: Fragment() {

    private var activityCallBack: TabWorkoutPlanFragmentListener? = null

    //FUNCTIONS AND INTERFACES
    interface TabWorkoutPlanFragmentListener {
        fun onButtonCreateExerciseClick()
    }
    override fun onAttach(context: Context)   {
        super.onAttach(context)
        activityCallBack =  context as TabWorkoutPlanFragmentListener
    }

    private var exercisesList: MutableList<ClassNumberOfExercises> = java.util.ArrayList()

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_workout_planner, container, false)
        val exercisesView = view.findViewById<RecyclerView>(R.id.workout_plan_view)
        val exercisesPicturesView =  view.findViewById<RecyclerView>(R.id.exercises_view)
        val createExerciseButton = view.findViewById<Button>(R.id.button_create2)

        createExerciseButton.setOnClickListener {
            activityCallBack!!.onButtonCreateExerciseClick()
        }

        exercisesList.add(ClassNumberOfExercises(10))
        exercisesList.add(ClassNumberOfExercises(8))
        exercisesList.add(ClassNumberOfExercises(5))
        exercisesList.add(ClassNumberOfExercises(11))


        getExercisesListInfoFromDB(exercisesPicturesView ,this@TabWorkoutPlanFragment.context!!)

        val adapter = WorkoutPlanAdapter(
            requireActivity(),
            this@TabWorkoutPlanFragment.context!!,
            exercisesList
        )
        exercisesView.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(this@TabWorkoutPlanFragment.context!!)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        exercisesView.layoutManager = linearLayoutManager
        return view
    }
}