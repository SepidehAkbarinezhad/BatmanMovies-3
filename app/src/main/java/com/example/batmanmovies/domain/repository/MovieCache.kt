package com.example.batmanmovies.domain.repository

import com.example.batmanmovies.data.entity.MovieData
import com.example.batmanmovies.presentation.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieCache {

    fun getMovies(): Flow<List<MovieEntity>>
    suspend fun insertAll(movies: List<MovieData>)
    suspend fun deleteAll()
}