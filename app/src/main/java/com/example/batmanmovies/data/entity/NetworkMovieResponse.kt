package com.example.batmanmovies.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkMovieResponse(
    @Json(name = "Search") var movieList: List<MovieData>,
    var totalResults: String,
    var Response: String
) {
}