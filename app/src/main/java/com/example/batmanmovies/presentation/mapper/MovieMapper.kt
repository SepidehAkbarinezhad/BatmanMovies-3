package com.example.batmanmovies.presentation.mapper

import com.example.batmanmovies.data.entity.MovieData
import com.example.batmanmovies.presentation.entity.MovieEntity


//MovieData to MovieEntity
fun mapMovieDataToMovieEntity(movieData: MovieData) = with(movieData) {
    MovieEntity(
        title,
        year,
        poster,
        imdbID,
        type
    )
}
fun MovieData.toEntity() = mapMovieDataToMovieEntity(this)
fun List<MovieData>.toEntity() = map {
    it.toEntity()
}

//MovieEntity to MovieData
fun mapMovieEntityToMovieData(movieEntity: MovieEntity) = with(movieEntity) {
    MovieData(
        title,
        year,
        poster,
        imdbID,
        type
    )
}
fun MovieEntity.toData() = mapMovieEntityToMovieData(this)
fun List<MovieEntity>.toData() = map {
    it.toData()
}



