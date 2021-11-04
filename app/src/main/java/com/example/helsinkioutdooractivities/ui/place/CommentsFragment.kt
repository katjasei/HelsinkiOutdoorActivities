package com.example.helsinkioutdooractivities.ui.place

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helsinkioutdooractivities.R
import com.example.helsinkioutdooractivities.adapters.CommentsAdapter
import com.example.helsinkioutdooractivities.data.model.CommentItem

class CommentsFragment: Fragment() {

    var commentsList: MutableList<CommentItem> = java.util.ArrayList()

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_comments, container, false)

        val commentsView = view.findViewById<RecyclerView>(R.id.comments_view)
        commentsList.add(CommentItem("Katja","Nice place", R.drawable.profile1))
        commentsList.add(CommentItem("Viktor","I don't like it at all", R.drawable.profile2))
        commentsList.add(CommentItem("Pekka","Good location", R.drawable.profile3))

        val adapter = CommentsAdapter(
            activity!!,
            this@CommentsFragment.context!!,
            commentsList
        )
        commentsView.adapter = adapter
        val linearLayoutManager = LinearLayoutManager(this@CommentsFragment.context!!)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        commentsView.layoutManager = linearLayoutManager
        return view
    }
}