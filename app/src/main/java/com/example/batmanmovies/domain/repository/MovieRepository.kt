package com.example.batmanmovies.domain.repository

import com.example.batmanmovies.presentation.entity.MovieEntity
import com.example.batmanmovies.utill.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository  {
     fun getMovies(): Flow<Resource<List<MovieEntity>>>

}