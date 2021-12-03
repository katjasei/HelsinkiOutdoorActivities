package com.example.helsinkioutdooractivities.utils

import android.app.Activity
import android.net.Uri
import android.widget.Toast
import com.example.helsinkioutdooractivities.data.model.ExerciseItem
import com.google.firebase.storage.FirebaseStorage
import java.util.*

fun uploadPhoto (pickedImageURI: Uri, exerciseName: String, areaOfFocus: String, instructions: String, activity: Activity){
    val mStorage = FirebaseStorage.getInstance().reference.child("${UUID.randomUUID()}.jpg")
    val imageFilePath = mStorage.child(pickedImageURI.lastPathSegment!!)

    imageFilePath
    .putFile(pickedImageURI)
    .addOnSuccessListener {
        imageFilePath.downloadUrl.addOnSuccessListener {
            val downloadURL = it.toString()
            val exercise = ExerciseItem(
                exerciseName,
                instructions,
                areaOfFocus,
                downloadURL
            )
            mFirebaseDB.child("exercises")
                //.child(UUID.randomUUID().toString())
               // .child("exercise_picture")
               // .setValue(downloadURL)
                .push()
                .setValue(exercise)

        }
        Toast.makeText(activity,"Uploaded", Toast.LENGTH_SHORT).show()
    }.addOnFailureListener {
        Toast.makeText(activity, "Failed$it", Toast.LENGTH_SHORT).show()
    }

}