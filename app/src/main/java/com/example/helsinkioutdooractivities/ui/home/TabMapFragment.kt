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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.helsinkioutdooractivities.R
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
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


class TabMapFragment: Fragment(), OnMapReadyCallback {

    private lateinit var currentLocation: LatLng
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val permissionCode = 101

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
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
        googleMap?.addMarker(markerOptions)

        val latLngPlace = LatLng(60.4372, 25.1420)
        val markerOptionsPlace = MarkerOptions().position(latLngPlace).title("Ulappasaarentie 3").icon(
            bitmapDescriptorFromVector(requireActivity(), R.drawable.ic_heart_svgrepo_com))
        googleMap?.addMarker(markerOptionsPlace)
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

        private const val URL = "https://www.googleapis.com/"
        object Model {

            data class DataResponse(
                val kind: String,
                val etag: String,
                val pageInfo: PageInfo,
                val items: List<PlaylistItems>
            )

            data class PageInfo (
                val totalResults: Int,
                val resultsPerPage: Int
            )

            data class PlaylistItems (
                val kind: String,
                val etag: String,
                val id: String,
                val snippet: Snippet
            )
            data class Snippet (
                val publishedAt: String,
                val channelID: String,
                val title: String,
                val description: String,
                val thumbnails: Thumbnails,
                val channelTitle: String,
                val localized: Localized
            )
            data class Localized (
                val title: String,
                val description: String)

            data class Thumbnails (
                val default: Description,
                val medium: Description,
                val high: Description,
                val standard: Description,
                val maxres: Description
            )
        }
        data class Description (
            val url: String,
            val width: Int,
            val height:Int)

        interface Service {
            @GET("youtube/v3/playlists")
            fun getPlaylists(
                @Query("part") part: String,
                @Query("maxResults") maxResults: Int,
                @Query("channelId") channelId: String,
                @Query("key") apiKey: String):
                    Call<Model.DataResponse>
        }

        private val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: Service = retrofit.create(
            Service::class.java)

    }
}