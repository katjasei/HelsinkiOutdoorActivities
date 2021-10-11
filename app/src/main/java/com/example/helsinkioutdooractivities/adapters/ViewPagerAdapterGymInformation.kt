package com.example.helsinkioutdooractivities.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.helsinkioutdooractivities.ui.place.CommentsFragment
import com.example.helsinkioutdooractivities.ui.place.EquipmentFragment
import com.example.helsinkioutdooractivities.ui.place.OverviewFragment

private const val NUM_TABS = 3

class ViewPagerAdapterGymInformation (fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle)  {
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return OverviewFragment()
            1 -> return EquipmentFragment()
        }
        return CommentsFragment()
    }
}