package com.example.batmanmovies.data.dataSource


import android.util.Log
import androidx.room.withTransaction
import com.example.batmanmovies.data.data.MovieDatabase
import com.example.batmanmovies.data.dataSource.movie.LocalMovieDataSource
import com.example.batmanmovies.data.dataSource.movie.RemoteMovieDataSource
import com.example.batmanmovies.domain.repository.MovieRepository
import com.example.batmanmovies.presentation.entity.MovieEntity
import com.example.batmanmovies.presentation.mapper.toData
import com.example.batmanmovies.presentation.utill.Constant.TAG
import com.example.batmanmovies.presentation.utill.Resource
import com.example.batmanmovies.presentation.utill.networkBoundResource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteMovieDataSource: RemoteMovieDataSource,
    private val localMovieDataSource: LocalMovieDataSource,
    private val database: MovieDatabase
) :
    MovieRepository {

    override fun getMovies(): Flow<Resource<List<MovieEntity>>> = networkBoundResource(
        query = { localMovieDataSource.getMovies() },
        fetch = { remoteMovieDataSource.getMovies() },
        saveFetchResult = { movies ->
            database.withTransaction {
                localMovieDataSource.deleteAll()
                localMovieDataSource.insertAll(movies.toData())
            }
        },
        shouldFetch = { movies ->
            movies.isNullOrEmpty()
        }
    )


}