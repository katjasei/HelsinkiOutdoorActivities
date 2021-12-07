package com.example.helsinkioutdooractivities.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helsinkioutdooractivities.R
import com.example.helsinkioutdooractivities.adapters.GymsAdapter
import com.example.helsinkioutdooractivities.data.model.GymListItem

class TabPlacesFragment: Fragment() {

    //VARIABLES
    private var activityCallBack: TabPlacesFragmentListener? = null
    var gymList: MutableList<GymListItem> = java.util.ArrayList()

    //FUNCTIONS AND INTERFACES
    interface TabPlacesFragmentListener {
        fun onClickableImageClick()
    }

    override fun onAttach(context: Context)   {
        super.onAttach(context)
        activityCallBack =  context as TabPlacesFragmentListener
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_places, container, false)
        val clickableImage = view.findViewById<ImageView>(R.id.clickable_image)
        val gymView = view.findViewById<RecyclerView>(R.id.gyms_view)

        clickableImage.setOnClickListener {
            activityCallBack!!.onClickableImageClick()
        }

        gymList.add(GymListItem("Merikorttitie 3","3,5 km", "https://www.tgogc.com/UserFiles/Images/NewsImages/TGO504%20Parallel%20Bars_Peckham%20Rye%20park%20(233).jpg"))
        gymList.add(GymListItem("Vanhatie 2","10 km", "https://www.lakewood.org/files/assets/public/community-resources/recreation/outdoor-fitness-ropes.jpg?w=1200"))

        val adapter = GymsAdapter(
            activity!!,
            this@TabPlacesFragment.context!!,
            gymList
        )

        gymView.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(this@TabPlacesFragment.context!!)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        gymView.layoutManager = linearLayoutManager

        return view
    }


}