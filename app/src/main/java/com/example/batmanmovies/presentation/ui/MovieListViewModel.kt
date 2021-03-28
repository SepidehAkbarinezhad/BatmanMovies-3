package com.example.batmanmovies.presentation.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.batmanmovies.domain.usecase.GetMoviesUseCase

class MovieListViewModel @ViewModelInject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) :
    ViewModel() {

    val movieList = getMoviesUseCase.execute(Unit).asLiveData()

}