package com.example.helsinkioutdooractivities.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.helsinkioutdooractivities.R
import com.example.helsinkioutdooractivities.data.model.ClassNumberOfExercises
import com.example.helsinkioutdooractivities.data.model.GymListItem
import kotlinx.android.synthetic.main.item_gym.view.*
import kotlinx.android.synthetic.main.item_workout_plan.view.*

class WorkoutPlanAdapter(private val activity: Activity, val context: Context, private val myDataset: List<ClassNumberOfExercises>) :
    RecyclerView.Adapter<WorkoutPlanAdapter.MyViewHolder>() {


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
            .inflate(R.layout.item_workout_plan, parent, false)
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


        holder.itemView.text_number_exercises.text =  exerciseItem.numberOfExercises.toString()
    }

    // Return the size of your data set (invoked by the layout manager)
    override fun getItemCount() = myDataset.size

    private fun isNetworkAvailable(): Boolean =
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).isDefaultNetworkActive

}