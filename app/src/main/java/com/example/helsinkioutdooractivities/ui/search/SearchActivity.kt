package com.example.helsinkioutdooractivities.ui.search

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.helsinkioutdooractivities.R
import com.example.helsinkioutdooractivities.ui.home.MainActivity
import com.example.helsinkioutdooractivities.utils.hideSystemUI
import com.example.helsinkioutdooractivities.utils.replaceFragment

class SearchActivity : AppCompatActivity(), SearchFragment.SearchFragmentListener{

    //VARIABLES:
    private val searchFragment = SearchFragment()

    //FUNCTIONS:
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        replaceFragment(searchFragment, supportFragmentManager)
        hideSystemUI(window)
    }

    override fun onButtonBackArrowClick() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("frgToLoad", 0)
        startActivity(intent)
    }


}