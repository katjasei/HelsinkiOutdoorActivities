package com.example.helsinkioutdooractivities.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helsinkioutdooractivities.R
import com.example.helsinkioutdooractivities.adapters.FavouritesAdapter
import com.example.helsinkioutdooractivities.data.model.FavouritesListItem
import com.google.android.material.button.MaterialButton

class TabFavourites: Fragment() {

    private var favouritesList: MutableList<FavouritesListItem> = java.util.ArrayList()

    private var activityCallBack: TabFavouritesListener? = null

    //FUNCTIONS AND INTERFACES
    interface TabFavouritesListener {
        fun onButtonLogOutClick()
    }
    override fun onAttach(context: Context)   {
        super.onAttach(context)
        activityCallBack =  context as TabFavouritesListener
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favourites, container, false)
        val favouritesView = view.findViewById<RecyclerView>(R.id.favourites_view)
        val btnLogout = view.findViewById<MaterialButton>(R.id.btn_logout)

        btnLogout.setOnClickListener {
            activityCallBack!!.onButtonLogOutClick()
        }

        favouritesList.add(FavouritesListItem("Gym 1","1", R.drawable.gym1))
        val adapter = FavouritesAdapter(
            activity!!,
            this@TabFavourites.context!!,
            favouritesList
        )
        favouritesView.adapter = adapter
        val gridLayoutManager = GridLayoutManager(this@TabFavourites.context!!, 2)
       // linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        favouritesView.layoutManager = gridLayoutManager

        return view
    }


}