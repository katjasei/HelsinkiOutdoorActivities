package com.example.helsinkioutdooractivities.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.helsinkioutdooractivities.R
import com.example.helsinkioutdooractivities.adapters.WorkoutAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class WorkoutFragment: Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_workout, container, false)

        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout_workout)
        val viewPager2 = view.findViewById<ViewPager2>(R.id.view_pager_workout)

        val adapter = WorkoutAdapter(childFragmentManager, lifecycle)

        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager2) { tab,position ->
            when (position) {
                0 -> {
                    tab.text = "Workout Planner"
                }
                1 -> {
                    tab.text = "Equipment"
                }
            }
        }.attach()

        return view
    }
}