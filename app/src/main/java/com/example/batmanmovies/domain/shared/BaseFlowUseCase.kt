package com.example.batmanmovies.domain.shared

import com.example.batmanmovies.utill.Resource
import kotlinx.coroutines.flow.Flow

abstract class BaseFlowUseCase<in P, R>() {

//    suspend operator fun invoke(parameter: P, dataSourceResponse: DataSourceResponse<R>) {
//
//        execute(parameter).catch { e ->
//            dataSourceResponse.error(Exception(e))
//        }
//
//    }


    abstract fun execute(parameter: P): Flow<Resource<R>>
}