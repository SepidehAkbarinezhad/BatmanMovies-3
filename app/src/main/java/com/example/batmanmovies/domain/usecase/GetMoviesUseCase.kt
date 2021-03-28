package com.example.batmanmovies.domain.usecase

import com.example.batmanmovies.domain.repository.MovieRepository
import com.example.batmanmovies.presentation.di.MovieRepoImpl
import com.example.batmanmovies.presentation.entity.MovieEntity
import com.example.batmanmovies.domain.shared.BaseFlowUseCase
import com.example.batmanmovies.presentation.utill.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    @MovieRepoImpl private val movieRepository: MovieRepository
) :
    BaseFlowUseCase<Unit, List<MovieEntity>>() {
    override fun execute(parameter: Unit): Flow<Resource<List<MovieEntity>>> = movieRepository.getMovies()

}