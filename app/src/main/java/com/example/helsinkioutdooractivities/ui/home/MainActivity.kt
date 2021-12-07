package com.example.helsinkioutdooractivities.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.helsinkioutdooractivities.R
import com.example.helsinkioutdooractivities.ui.auth.AuthActivity
import com.example.helsinkioutdooractivities.ui.place.GymInformationFragment
import com.example.helsinkioutdooractivities.ui.place.PlaceActivity
import com.example.helsinkioutdooractivities.ui.search.SearchActivity
import com.example.helsinkioutdooractivities.utils.hideSystemUI
import com.example.helsinkioutdooractivities.utils.replaceFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity(), TabPlacesFragment.TabPlacesFragmentListener,
TabFavourites.TabFavouritesListener, TabWorkoutPlanFragment.TabWorkoutPlanFragmentListener,
AddExerciseFragment.AddExerciseFragmentListener, PopUpWindowDialog.DialogFragmentListener{

    //VARIABLES:
    private val homeFragment = HomeFragment()
    //firebase auth object
    private var mFirebaseAuth = FirebaseAuth.getInstance()
    private val gymInformationFragment = GymInformationFragment()
    val bundle = Bundle()


    //FUNCTIONS:
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        when (intent.extras!!.getInt("frgToLoad")) {
            0 -> {
                replaceFragment(homeFragment, supportFragmentManager)
            }
            1 -> {
                replaceFragment(TabMapFragment(), supportFragmentManager)
            }
            2 -> {
                replaceFragment(TabPlacesFragment(), supportFragmentManager)
            }

            3-> {
                replaceFragment(TabWorkoutPlanFragment(), supportFragmentManager)
            }
        }

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener(bottomNavigationOnClickListener)

        val buttonSearch = findViewById<Button>(R.id.search_button)

        //FAB - set white tint for icon
        val myButtonSrc = resources.getDrawable(R.drawable.ic_baseline_search_24,null)
        val willBeBlack = myButtonSrc?.constantState?.newDrawable()
        willBeBlack?.mutate()?.setColorFilter(Color.DKGRAY, PorterDuff.Mode.MULTIPLY)
        buttonSearch?.setCompoundDrawables(null, willBeBlack, null, null)

        buttonSearch.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        hideSystemUI(window)
    }

    private val bottomNavigationOnClickListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.workouts -> {
                Log.i("TAG", "${item.title} pressed")
                    replaceFragment(WorkoutFragment(), supportFragmentManager)

                return@OnNavigationItemSelectedListener true
            }
            R.id.home -> {
                Log.i("TAG", "${item.title} pressed")
                replaceFragment(HomeFragment(), supportFragmentManager)
                return@OnNavigationItemSelectedListener true
            }
            R.id.weather -> {
                Log.i("TAG", "${item.title} pressed")
                    replaceFragment(WeatherFragment(), supportFragmentManager)
                return@OnNavigationItemSelectedListener true
            }
            R.id.profile -> {
                Log.i("TAG", "${item.title} pressed")

                    replaceFragment(TabProfileFragment(), supportFragmentManager)

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onClickableImageClick() {
        //run new activity
        //move to PlaceActivity
        val intent = Intent(this, PlaceActivity::class.java)
        intent.putExtra("Address",  "Ulappansaarentie 3")
        intent.putExtra("GymImage", "https://outdoor-gym.com/wp-content/uploads/outdoor-gym-and-outdoor-fitness-parks.jpg")
        intent.putExtra("Distance", "2,3 km")
        startActivity(intent)
    }

    override fun onButtonLogOutClick() {
        //user logout
        mFirebaseAuth.signOut()
        //start new Activity - go to FirstScreen/LogIn, SighUp screen
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
    }

    override fun onButtonCreateExerciseClick() {
        replaceFragment(AddExerciseFragment(), supportFragmentManager)
    }

    override fun onButtonArrowBackClicked() {
        replaceFragment(TabWorkoutPlanFragment(), supportFragmentManager)
    }

    override fun onButtonMoreInfoClicked(
        address: String,
        distance: String,
        pictureUrl: String,
        name: String
    ) {
        bundle.putString("Address", address)
        bundle.putString("GymImage", pictureUrl)
        bundle.putString("Distance", distance)

        gymInformationFragment.arguments = bundle

        replaceFragment(gymInformationFragment, supportFragmentManager)
    }

}