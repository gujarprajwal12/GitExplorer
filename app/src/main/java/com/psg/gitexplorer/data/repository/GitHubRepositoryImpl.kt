package com.psg.gitexplorer.data.repository

import com.psg.gitexplorer.data.local.FavoriteEntity
import com.psg.gitexplorer.data.local.FavoritesDao
import com.psg.gitexplorer.data.model.Repository
import com.psg.gitexplorer.data.remote.RetrofitClient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubRepositoryImpl @Inject constructor(
    private val retrofitClient: RetrofitClient,
    private val favoritesDao: FavoritesDao
) : GitHubRepository {
    override suspend fun searchRepositories(query: String): List<Repository> {
        val response = retrofitClient.apiService.searchRepositories(query)
        return response.items
    }

    override fun getFavorites(): Flow<List<FavoriteEntity>> = favoritesDao.getAll()

    override suspend fun addFavorite(item: FavoriteEntity) = favoritesDao.insert(item)

    override suspend fun removeFavorite(item: FavoriteEntity) = favoritesDao.delete(item)

    override suspend fun isFavorite(id: Long): Boolean = favoritesDao.exists(id)
}
