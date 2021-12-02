package com.example.helsinkioutdooractivities.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.helsinkioutdooractivities.R
import com.example.helsinkioutdooractivities.data.model.ClassLocation
import com.example.helsinkioutdooractivities.data.model.GymListItem
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


class TabMapFragment: Fragment(), OnMapReadyCallback {

    private lateinit var currentLocation: LatLng
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val permissionCode = 101
    var locationList: MutableList<ClassLocation> = java.util.ArrayList()
    private var locationMap: GoogleMap? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        fetchLocation()
        return view
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val floatingActionButton = view?.findViewById<FloatingActionButton>(R.id.floating_action_button)

        floatingActionButton?.setOnClickListener {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext().applicationContext)
            val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
            mapFragment.getMapAsync(this)
        }

        //FAB - set white tint for icon
        val myFabSrc = resources.getDrawable(R.drawable.ic_my_location_white_24dp,null)
        val willBeWhite = myFabSrc?.constantState?.newDrawable()
        willBeWhite?.mutate()?.setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY)
        floatingActionButton?.setImageDrawable(willBeWhite)

        callWebservice()

    }

    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), permissionCode)
            return
        }
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext().applicationContext)

        val task = fusedLocationProviderClient.lastLocation
        task.addOnSuccessListener { location ->
            if (location != null) {
                currentLocation = LatLng(location.latitude, location.longitude)
                Toast.makeText(context, currentLocation.latitude.toString() + "" +
                        currentLocation.longitude, Toast.LENGTH_SHORT).show()
                val supportMapFragment = (childFragmentManager.findFragmentById(R.id.map) as
                        SupportMapFragment?)!!
                supportMapFragment.getMapAsync(this)
            }
        }
    }
    override fun onMapReady(googleMap: GoogleMap?) {
        val latLng = LatLng(currentLocation.latitude, currentLocation.longitude)
        val markerOptions = MarkerOptions().position(latLng).title("Your current location").icon(
            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        googleMap?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))
        googleMap?.addMarker(markerOptions)

        locationMap = googleMap
    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>,
                                            grantResults: IntArray) {
        when (requestCode) {
            permissionCode -> if (grantResults.isNotEmpty() && grantResults[0] ==
            PackageManager.PERMISSION_GRANTED) {
            fetchLocation()
        }
        }
    }


    object DemoApi {

        private const val URL = "https://open-api.myhelsinki.fi/"
        object Model {

            data class DataResponse(
               val meta: Meta,
               val data: List<Data>,
               val tags: Tags
               )

            data class Meta(
                val count: String
            )

            data class Data(
                val id: String,
                val name: Name,
                val sourceType: SourceType,
                val infoUrl: String,
                val modifiedAt: String,
                val location: LocationClass,
                val description: Description,
                val tags: List<Tag>,
                val openingHours: OpeningHours,
                val extra_searchword: List<String>
            )

            data class Tags(
                val tag: String
            )

            data class Name(
                val fi: String,
                val en: String,
                val sv: String,
                val zh: String
            )

            data class SourceType(
                val id: Int,
                val name: String
            )

            data class LocationClass(
                val lat: Double,
                val lon: Double,
                val address: Address
            )

            data class Address(
                val street_address: String,
                val postal_code: String,
                val locality: String,
                val neighbourhood: String
            )

            data class Description(
                val intro: String,
                val body: String,
                val images: List<Image>
            )

            data class Image(
                val url: String,
                val copyrightHolder: String,
                val licenseType: String,
                val media_id: String
            )

            data class Tag(
                val id: String,
                val name: String
            )

            data class OpeningHours(
                val hours: List<Hours>,
                val openinghoursException: String
            )

            data class Hours(
                val weekdayId: Int,
                val opens: Opens,
                val closes: Closes,
                val open24h: Boolean
            )

            data class Opens(
                val hours: Int,
                val minutes: Int,
                val seconds: Int
            )

            data class Closes(
                val hours: Int,
                val minutes: Int,
                val seconds: Int
            )
        }

        interface Service {
            //@Headers("accept: application/json")
            @GET("v1/places/")
            fun getLocations(@Query("tags_filter") tags_filter: String):
                    Call<Model.DataResponse>
        }

        private val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: Service = retrofit.create(
            Service::class.java)

    }


    private fun callWebservice(){

        val call = DemoApi.service.getLocations("NATURE & SPORTS")
        val value = object: Callback<DemoApi.Model.DataResponse>

        {
            override fun onResponse(call: Call<DemoApi.Model.DataResponse>, response: Response<DemoApi.Model.DataResponse>) {

                val res: DemoApi.Model.DataResponse = response.body()!!

               // println(res.data)

                res.data.forEach {
                    locationList.add(ClassLocation(it.location.address.street_address, it.name.en, it.location.lat, it.location.lon))
                }

                println(locationList)
           locationList.forEach {
               if(it.lat!=null && it.lon!=null) {
               val latLngPlace = LatLng(it.lat!!, it.lon!!)
               val markerOptionsPlace = MarkerOptions().position(latLngPlace).title(it.address).icon(
                   bitmapDescriptorFromVector(requireActivity(), R.drawable.ic_heart_svgrepo_com))
                locationMap?.addMarker(markerOptionsPlace)}
           }
                locationMap?.setOnInfoWindowClickListener {
                  PopUpWindowDialog.newInstance("katja","katja").show(parentFragmentManager, PopUpWindowDialog.TAG)
                }
            }

            override fun onFailure(call: Call<DemoApi.Model.DataResponse>, t: Throwable) {
                Log.e("DBG", t.toString());       }
        }

        call.enqueue(value) // asynchronous request

    }

}