package com.example.batmanmovies.presentation.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.batmanmovies.R
import com.example.batmanmovies.data.data.MovieDatabase
import com.example.batmanmovies.data.dataSource.MovieRepositoryImpl
import com.example.batmanmovies.data.dataSource.movie.LocalMovieDataSource
import com.example.batmanmovies.data.dataSource.movie.RemoteMovieDataSource
import com.example.batmanmovies.data.remote.ApiService
import com.example.batmanmovies.domain.repository.MovieRepository
import com.example.batmanmovies.utill.Constant.BASE_URL
import com.example.batmanmovies.utill.Constant.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        app: Application
    ) = Room.databaseBuilder(app, MovieDatabase::class.java, DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideTaskDao(db: MovieDatabase) = db.movieDao()

    @Singleton
    @Provides
    fun provideRetrofit(moshiConverterFactory: MoshiConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Provides
    fun providesMoshiConverterFactory(): MoshiConverterFactory {
        return MoshiConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit) = retrofit.create(ApiService::class.java)


    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

    @Provides
    fun provideOmp(
        remoteMovieDataSource: RemoteMovieDataSource,
        localMovieDataSource: LocalMovieDataSource,
        database: MovieDatabase
    ): MovieRepository {
        return MovieRepositoryImpl(remoteMovieDataSource, localMovieDataSource, database)
    }

    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.batman)
            .error(R.drawable.batman)
    )

}