package com.example.batmanmovies.presentation.utill

sealed class Resource<T>(
    val data: T? = null,
    val throwable: Throwable? = null
) {

    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T?) : Resource<T>(data)
    class Error<T>(throwable: Throwable, data: T?) : Resource<T>(data,throwable)
}