package com.example.helsinkioutdooractivities.ui.home

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.helsinkioutdooractivities.R
import com.example.helsinkioutdooractivities.utils.WeatherApiConnection
import com.example.helsinkioutdooractivities.utils.WeatherApiResponseParser
import kotlinx.android.synthetic.main.fragment_weather.*
import java.lang.Error
import java.net.HttpURLConnection
import java.net.URL
import kotlin.math.roundToInt

class WeatherFragment: Fragment(){

    private val handler: Handler = object :
        Handler(Looper.getMainLooper()) {
        @SuppressLint("SetTextI18n")
        override fun handleMessage(inputMessage: Message) {
            if (inputMessage.what == 0) {
                //parse response
                val weatherObject = WeatherApiResponseParser.parse(inputMessage.obj.toString())
                //set weather description
                Log.i("weather", weatherObject.weather[0].main)
                weatherDescriptionTextView?.text = weatherObject.weather[0].description
                //set temperature
                temperatureTextView?.text = "${weatherObject.main.temp.roundToInt()}°C"
                //set location
                locationTextView?.text = weatherObject.name
                //get possible icon url
                val icon: String? = weatherObject.weather[0].icon
                //if weather icon is found, display it
                if (icon != null) {
                    WeatherApiConnectionForImage().execute(URL(icon))
                }
                else {
                    Log.d("weather", "Icon was null")
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val weatherConnection =
            WeatherApiConnection(handler)
        val weatherThread = Thread(weatherConnection)
        weatherThread.start()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    @SuppressLint("StaticFieldLeak")
    inner class WeatherApiConnectionForImage : AsyncTask<URL, Unit, Bitmap>()  {
        private lateinit var result: Bitmap
        override fun doInBackground(vararg urlParams: URL): Bitmap {
            result = try {
                val imageConnection =  urlParams[0].openConnection() as HttpURLConnection
                val inputStream = imageConnection.inputStream
                BitmapFactory.decodeStream(inputStream)
            } catch (e: Error){
                Log.i("TAG", "Error getting weather icon: ${e.message}")
                BitmapFactory.decodeFile("drawable/home_icon.xml")
            }
            return result
        }
        override fun onPostExecute(result: Bitmap?) {
            val imageView = weatherImageView
            imageView?.setImageBitmap(result)
        }
    }
}