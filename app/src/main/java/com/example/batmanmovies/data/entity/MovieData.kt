package com.example.batmanmovies.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movie_table")
@Parcelize
@JsonClass(generateAdapter = true)
data class MovieData(
    @Json(name = "Title") val title: String,
    @Json(name = "Year") val year: String,
    @PrimaryKey
    @Json(name = "imdbID") val imdbID: String,
    @Json(name = "Type") val type: String,
    @Json(name = "Poster") val poster: String
) : Parcelable