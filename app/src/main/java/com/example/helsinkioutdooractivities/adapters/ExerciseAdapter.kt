package com.example.helsinkioutdooractivities.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.helsinkioutdooractivities.R
import com.example.helsinkioutdooractivities.data.model.ExerciseItem
import com.example.helsinkioutdooractivities.data.model.FavouritesListItem
import com.example.helsinkioutdooractivities.ui.exercise.ExerciseActivity
import com.example.helsinkioutdooractivities.ui.place.PlaceActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_workout_planner.view.*
import kotlinx.android.synthetic.main.item_exercise.view.*
import kotlinx.android.synthetic.main.item_favourites.view.*
import kotlinx.android.synthetic.main.item_gym.view.*
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class ExerciseAdapter (val context: Context, private val myDataset: List<ExerciseItem>) :
    RecyclerView.Adapter<ExerciseAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)



    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {

        // create a new view
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(
            view
        )
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your data set at this position
        // - replace the contents of the view with that element

        val exerciseItem = myDataset[position]

        holder.itemView.card_view_exercise.setOnClickListener {
            val intent = Intent(it.context, ExerciseActivity::class.java)

            intent.putExtra("ExercisePicture",  exerciseItem.exercise_picture)
            intent.putExtra("ExerciseName",  exerciseItem.exercise_name)
            intent.putExtra("Instructions", exerciseItem.instructions)
            intent.putExtra("AreaOfFocus", exerciseItem.area_of_focus)

            context.startActivity(intent)
        }

        Picasso.get().load(exerciseItem.exercise_picture).into(holder.itemView.exercise_image)
    }

    // Return the size of your data set (invoked by the layout manager)
    override fun getItemCount() = myDataset.size

    private fun isNetworkAvailable(): Boolean =
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).isDefaultNetworkActive

}