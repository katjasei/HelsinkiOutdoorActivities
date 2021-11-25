package com.example.helsinkioutdooractivities.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helsinkioutdooractivities.R
import com.example.helsinkioutdooractivities.adapters.WorkoutPlanAdapter
import com.example.helsinkioutdooractivities.data.model.ClassNumberOfExercises

class TabWorkoutPlanFragment: Fragment() {

    var exercisesList: MutableList<ClassNumberOfExercises> = java.util.ArrayList()


    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_workout_planner, container, false)
        val exercisesView = view.findViewById<RecyclerView>(R.id.workout_plan_view)

        exercisesList.add(ClassNumberOfExercises(10))
        exercisesList.add(ClassNumberOfExercises(8))
        exercisesList.add(ClassNumberOfExercises(5))
        exercisesList.add(ClassNumberOfExercises(11))

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