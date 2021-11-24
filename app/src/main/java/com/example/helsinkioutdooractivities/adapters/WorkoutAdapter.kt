package com.example.helsinkioutdooractivities.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.helsinkioutdooractivities.ui.home.TabEquipmentWorkoutFragment
import com.example.helsinkioutdooractivities.ui.home.TabMapFragment
import com.example.helsinkioutdooractivities.ui.home.TabPlacesFragment
import com.example.helsinkioutdooractivities.ui.home.TabWorkoutPlanFragment

private const val NUM_TABS = 2

class WorkoutAdapter (fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle)  {
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return TabWorkoutPlanFragment()
        }
        return TabEquipmentWorkoutFragment()
    }
}