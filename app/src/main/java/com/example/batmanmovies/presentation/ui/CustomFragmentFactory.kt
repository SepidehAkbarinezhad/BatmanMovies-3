package com.example.batmanmovies.presentation.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject

class CustomFragmentFactory @Inject constructor(
    private val movieListAdapter: MovieListAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            MovieListFragment::class.java.name -> MovieListFragment(movieListAdapter)
            else -> super.instantiate(classLoader, className)
        }


    }
}