package com.example.batmanmovies.presentation.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.batmanmovies.MainActivity
import com.example.batmanmovies.R
import com.example.batmanmovies.databinding.FragmentMovieListBinding
import com.example.batmanmovies.utill.Constant.TAG
import com.example.batmanmovies.utill.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieListFragment : BaseFragment(R.layout.fragment_movie_list) {

    lateinit var movieListBinding: FragmentMovieListBinding
    private val viewModel: MovieListViewModel by viewModels()


    @Inject
    lateinit var moviesAdapter: MovieListAdapter


    override fun bind(view: View) {
        super.bind(view)
        customToolbar.setToolbarTitle("batman movies")
        movieListBinding = FragmentMovieListBinding.bind(view)


        movieListBinding.apply {
            rcvMoviesList.adapter = moviesAdapter
        }

        moviesAdapter.setOnItemClickListener {
            findNavController().navigate(MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment())
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

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_movies_fragment, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

    }

}