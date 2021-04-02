package com.example.batmanmovies.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.batmanmovies.data.data.MovieDao
import com.example.batmanmovies.data.data.MovieDatabase
import com.example.batmanmovies.data.entity.MovieData
import com.example.batmanmovies.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MovieDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var movieDatabase: MovieDatabase
    private lateinit var movieDao: MovieDao

    @Before
    fun setup() {
        movieDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MovieDatabase::class.java
        ).allowMainThreadQueries().build()

        movieDao = movieDatabase.movieDao()
    }

    @After
    fun teardown() {
        movieDatabase.close()
    }

    @Test
    fun insertMovie() = runBlockingTest {
        val movieData = MovieData(
            "title",
            "year",
            "imdbId",
            "type",
            "poster"
        )
        movieDao.insert(movieData)

        val movieList = movieDao.getMovies().asLiveData().getOrAwaitValue()
        assertThat(movieList).contains(movieData)
    }

    @Test
    fun insertMovieItems() = runBlockingTest {
        val movieData1 = MovieData(
            "title_1",
            "year_1",
            "imdbId_1",
            "type_1",
            "poster_1"
        )
        val movieData2 = MovieData(
            "title_2",
            "year_2",
            "imdbId_2",
            "type_2",
            "poster_2"
        )
        val movies = listOf(movieData1, movieData2)
        movieDao.insertAll(movies)

        val movieList = movieDao.getMovies().asLiveData().getOrAwaitValue()
        assertThat(movieList.size).isEqualTo(2)
    }

    @Test
    fun deleteMovieItem() = runBlockingTest {

        val movieData = MovieData(
            "title",
            "year",
            "imdbId",
            "type",
            "poster"
        )

        movieDao.insert(movieData)
        movieDao.delete(movieData)

        val movieList = movieDao.getMovies().asLiveData().getOrAwaitValue()
        assertThat(movieList).doesNotContain(movieData)
    }

}