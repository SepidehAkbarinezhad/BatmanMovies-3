package com.example.batmanmovies.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.batmanmovies.R
import com.example.batmanmovies.databinding.FragmentMovieListBinding
import com.example.batmanmovies.presentation.utill.Constant.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private val viewModel : MovieListViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieListBinding: FragmentMovieListBinding = FragmentMovieListBinding.bind(view)
        viewModel.movieList.observe(viewLifecycleOwner, Observer {result->
            result?.let {

                Log.d(TAG, "onViewCreated: ${it.data?.size}")
            }
        })

    }


}