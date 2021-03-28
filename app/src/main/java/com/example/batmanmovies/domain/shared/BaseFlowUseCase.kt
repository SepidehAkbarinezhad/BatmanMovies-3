package com.example.batmanmovies.domain.shared

import android.util.Log
import com.example.batmanmovies.presentation.utill.Constant.TAG
import com.example.batmanmovies.presentation.utill.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import java.lang.Exception

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