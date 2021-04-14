package com.example.batmanmovies.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.batmanmovies.R

class MovieDetailFragment : BaseFragment(R.layout.fragment_movie_detail) {


    override fun bind(view: View) {
        super.bind(view)
        customToolbar.setToolbarTitle("movie detail")
    }

}