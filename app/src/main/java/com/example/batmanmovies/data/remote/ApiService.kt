package com.example.batmanmovies.data.remote

import com.example.batmanmovies.data.entity.MovieData
import com.example.batmanmovies.data.entity.NetworkMovieResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(".")
    suspend fun getMovies(
        @Query("apikey") apikey: String,
        @Query("s") name: String
    ): NetworkMovieResponse
}