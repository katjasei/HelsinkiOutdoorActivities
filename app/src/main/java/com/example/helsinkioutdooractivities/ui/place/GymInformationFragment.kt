package com.example.helsinkioutdooractivities.ui.place

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.helsinkioutdooractivities.R
import com.example.helsinkioutdooractivities.adapters.ViewPagerAdapter
import com.example.helsinkioutdooractivities.adapters.ViewPagerAdapterGymInformation
import com.example.helsinkioutdooractivities.ui.home.MainActivity
import com.example.helsinkioutdooractivities.ui.home.TabPlacesFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class GymInformationFragment: Fragment() {

    //public val placesFragment = TabPlacesFragment();

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_gym_informatin, container, false)
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout_gym)
        val viewPager2 = view.findViewById<ViewPager2>(R.id.viewPager)

        val adapter = ViewPagerAdapterGymInformation(childFragmentManager, lifecycle)

        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager2) { tab,position ->
            when (position) {
                0 -> {
                    tab.text = "Overview"
                }
                1 -> {
                    tab.text = "Equipment"
                }
                2 -> {
                    tab.text = "Comments"
                }

            }
        }.attach()
        return view
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val arrowBackFab = view?.findViewById<FloatingActionButton>(R.id.arrow_back_button)

        //FAB - set white tint for icon
        val myFabSrc = resources.getDrawable(R.drawable.ic_baseline_arrow_back_24,null)
        val willBeWhite = myFabSrc?.constantState?.newDrawable()
        willBeWhite?.mutate()?.setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY)
        arrowBackFab?.setImageDrawable(willBeWhite)

        arrowBackFab?.setOnClickListener{
            //move to MainActivity (HomeActivity)
            val intent = Intent(this.context, MainActivity::class.java)
           // val fragmentString = "FRAGMENT_A"
            intent.putExtra("frgToLoad", 2)
            startActivity(intent)

        }
    }
}