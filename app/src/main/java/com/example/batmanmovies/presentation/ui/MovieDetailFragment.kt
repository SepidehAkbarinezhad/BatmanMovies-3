package com.example.batmanmovies.presentation.ui


import android.view.View
import com.example.batmanmovies.R

class MovieDetailFragment : BaseFragment(R.layout.fragment_movie_detail) {


    override fun bind(view: View) {
        super.bind(view)
        customToolbar.setToolbarTitle("movie detail")
    }

}