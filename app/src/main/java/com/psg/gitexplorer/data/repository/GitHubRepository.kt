package com.psg.gitexplorer.data.repository

import com.psg.gitexplorer.data.local.FavoriteEntity
import com.psg.gitexplorer.data.model.Repository
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {
    suspend fun searchRepositories(query: String): List<Repository>
    fun getFavorites(): Flow<List<FavoriteEntity>>
    suspend fun addFavorite(item: FavoriteEntity)
    suspend fun removeFavorite(item: FavoriteEntity)
    suspend fun isFavorite(id: Long): Boolean
}
