package com.example.batmanmovies.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.batmanmovies.R
import com.example.batmanmovies.databinding.FragmentMovieListBinding
import com.example.batmanmovies.utill.Constant.TAG
import com.example.batmanmovies.utill.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private val viewModel: MovieListViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieListBinding: FragmentMovieListBinding = FragmentMovieListBinding.bind(view)
        val moviesAdapter = MovieListAdapter()
        movieListBinding.apply {
            rcvMoviesList.adapter = moviesAdapter
        }

        viewModel.movieList.observe(viewLifecycleOwner, Observer { result ->
            result?.let {
                moviesAdapter.submitList(it.data)

                movieListBinding.apply {
                    progress.isVisible = it is Resource.Loading && it.data.isNullOrEmpty()
                    with(errorMsg) {
                        isVisible = it is Resource.Error && it.data.isNullOrEmpty()
                        text = it.throwable?.localizedMessage
                    }
                }
            }
        })
    }

}