package com.example.helsinkioutdooractivities

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.helsinkioutdooractivities.ui.home.TabMapFragment
import com.example.helsinkioutdooractivities.ui.home.TabPlacesFragment
import com.example.helsinkioutdooractivities.ui.home.TabProfileFragment

private const val NUM_TABS = 3

class ViewPagerAdapter (fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle)  {
    override fun getItemCount(): Int {
       return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return TabMapFragment()
            1 -> return TabPlacesFragment()
        }
        return TabProfileFragment()
    }
}