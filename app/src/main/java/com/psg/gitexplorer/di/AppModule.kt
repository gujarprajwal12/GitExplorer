package com.psg.gitexplorer.di

import android.content.Context
import androidx.room.Room
import com.psg.gitexplorer.data.local.FavoritesDao
import com.psg.gitexplorer.data.local.FavoritesDatabase
import com.psg.gitexplorer.data.remote.RetrofitClient
import com.psg.gitexplorer.data.remote.GitHubApiService
import com.psg.gitexplorer.data.repository.GitHubRepository
import com.psg.gitexplorer.data.repository.GitHubRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {
    @Binds
    abstract fun bindRepository(impl: GitHubRepositoryImpl): GitHubRepository
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofitClient(): RetrofitClient = RetrofitClient()

    @Provides
    @Singleton
    fun provideApiService(retrofitClient: RetrofitClient): GitHubApiService = retrofitClient.apiService

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FavoritesDatabase {
        return Room.databaseBuilder(context, FavoritesDatabase::class.java, "favorites_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideFavoritesDao(db: FavoritesDatabase): FavoritesDao = db.favoritesDao()
}
