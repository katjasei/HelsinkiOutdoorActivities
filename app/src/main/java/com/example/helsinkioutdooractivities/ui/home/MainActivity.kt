package com.example.helsinkioutdooractivities.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.helsinkioutdooractivities.R
import com.example.helsinkioutdooractivities.ui.place.GymInformationFragment
import com.example.helsinkioutdooractivities.ui.place.PlaceActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(), TabPlacesFragment.TabPlacesFragmentListener {

    //VARIABLES:
    private val homeFragment = HomeFragment()

    //FUNCTIONS:
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentFragment = intent.extras!!.getInt("frgToLoad")

        when (intentFragment) {
            0 -> {
                replaceFragment(homeFragment)
            }
            1 -> {
                replaceFragment(TabMapFragment())
            }
            2 -> {
                replaceFragment(TabPlacesFragment())
            }
        }

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener(bottomNavigationOnClickListener)

        hideSystemUI()
    }

    private val bottomNavigationOnClickListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.workouts -> {
                Log.i("TAG", "${item.title} pressed")
                    replaceFragment(WorkoutFragment())

                return@OnNavigationItemSelectedListener true
            }
            R.id.home -> {
                Log.i("TAG", "${item.title} pressed")
                replaceFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.profile -> {
                Log.i("TAG", "${item.title} pressed")

                    replaceFragment(TabProfileFragment())

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun hideSystemUI() {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    override fun onClickableImageClick() {
        //run new activity
        //move to PlaceActivity
        val intent = Intent(this, PlaceActivity::class.java)
        startActivity(intent)
    }

    //function used for fragment replacement
    private fun replaceFragment(fragment: Fragment){
        //fragment manager can help when switching to the other fragment is needed
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}