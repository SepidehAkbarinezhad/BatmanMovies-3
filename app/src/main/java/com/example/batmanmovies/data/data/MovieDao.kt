package com.example.batmanmovies.data.data

import androidx.room.*
import com.example.batmanmovies.data.entity.MovieData
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_table")
    fun getMovies(): Flow<List<MovieData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieData: MovieData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieData>)

    @Update
    suspend fun update(movieData: MovieData)

    @Delete
    suspend fun delete(movieData: MovieData)

    @Query("DELETE FROM movie_table")
    suspend fun deleteAll()
}