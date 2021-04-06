package com.example.batmanmovies.utill

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: suspend (ResultType) -> Boolean = { true }

) : Flow<Resource<ResultType>> = flow {

    val resultType = query().first()
    val flow = if (shouldFetch(resultType)) {
        emit(Resource.Loading(resultType))
        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        } catch (t: Throwable) {
            query().map { Resource.Error(t, it) }
        }

    } else
        query().map {
            Resource.Success(it)
        }

    emitAll(flow )
}