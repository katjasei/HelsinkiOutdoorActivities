package com.example.helsinkioutdooractivities.ui.home

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.helsinkioutdooractivities.R
import com.example.helsinkioutdooractivities.utils.uploadPhoto
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_add_exercise.*
import kotlinx.android.synthetic.main.fragment_profile.*
import java.io.File


class AddExerciseFragment  : Fragment() {

    private var activityCallBack: AddExerciseFragmentListener? = null
    private val alertItems = arrayOf("Open camera","Choose from library")
    private val REQUEST_IMAGE_CAPTURE = 99
    private val REQUESTCODE = 1
    private var mCurrentPhotoPath = ""
    private lateinit var uri: Uri


    //FUNCTIONS AND INTERFACES
    interface AddExerciseFragmentListener {
        fun onButtonArrowBackClicked()
    }
    override fun onAttach(context: Context)   {
        super.onAttach(context)
        activityCallBack =  context as AddExerciseFragmentListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_exercise, container, false)
        val arrowBackButton = view.findViewById<FloatingActionButton>(R.id.arrow_back_button_add_exercise)

       arrowBackButton.setOnClickListener {
          activityCallBack!!.onButtonArrowBackClicked()
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        exercise_picture.setOnClickListener {
            showAlertDialog()
        }

        btn_add_exercise.setOnClickListener {
            uploadPhoto(uri,
                value_exercise_name.text.toString(),
                value_area_of_focus.text.toString(),
                value_instruction.text.toString(),
                requireActivity())

        }

    }


    //FUNCTIONS FOR UPLOADING PROFILE PICTURE
    //Alert dialog with to choices: Open camera or Choice from library
    private fun showAlertDialog(){
        val builder = AlertDialog.Builder(this.context)
        builder.setTitle("Upload exercise picture")
        builder.setSingleChoiceItems(
            alertItems,
            -1
        ) { _, which ->
            if(which == 0){
                openCamera()
            } else {
                openGallery()
            }
        }
        builder.setPositiveButton(
            "OK"
        ) { _, _ ->

        }
        val dialog = builder.create()
        // Display the alert dialog on interface
        dialog.show()
    }

    private fun openGallery(){
        //open gallery intent and wait for user to pick an image
        val galleryIntent = Intent(Intent.ACTION_GET_CONTENT)
        galleryIntent.type = "image/*"
        startActivityForResult(galleryIntent, REQUESTCODE)
    }

    private fun openCamera(){
        val fileName = "temp_photo"
        val imgPath = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val imageFile: File?
        imageFile = File.createTempFile(fileName, ".jpg", imgPath)
        val photoURI: Uri =
            FileProvider.getUriForFile(this.requireContext(), "com.example.plogging", imageFile)
        mCurrentPhotoPath = imageFile!!.absolutePath
        val myIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (myIntent.resolveActivity(requireContext().packageManager) != null) {
            myIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            startActivityForResult(myIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == REQUESTCODE && data != null ){
            //the user has successfully picked an image
            //we need to save its reference to a URI variable
            val pickedImageURI = data.data!!
            exercise_picture.setImageURI(pickedImageURI)
            uri = pickedImageURI
            //upload user photo to firebase storage and get url
            //

           //uploadPhoto(pickedImageURI, requireActivity())
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = BitmapFactory.decodeFile(mCurrentPhotoPath)
            uri = Uri.fromFile(File(mCurrentPhotoPath))
            //uploadPhoto(Uri.fromFile(File(mCurrentPhotoPath)), requireActivity())
            exercise_picture.setImageBitmap(imageBitmap)
        }
    }



}