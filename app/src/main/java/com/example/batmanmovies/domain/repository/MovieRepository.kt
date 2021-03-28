package com.example.batmanmovies.domain.repository

import com.example.batmanmovies.data.entity.MovieData
import com.example.batmanmovies.presentation.entity.MovieEntity
import com.example.batmanmovies.presentation.utill.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository  {
     fun getMovies(): Flow<Resource<List<MovieEntity>>>

}