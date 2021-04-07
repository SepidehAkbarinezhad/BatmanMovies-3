package com.example.batmanmovies.di

import android.content.Context
import androidx.room.Room
import com.example.batmanmovies.data.data.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Named("test_db")
    @Provides
    fun provideInMemoryDataBase(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(
            context, MovieDatabase::class.java
        ).allowMainThreadQueries()
            .build()
}