package com.psg.gitexplorer.domain.usecase

import com.psg.gitexplorer.data.local.FavoriteEntity
import com.psg.gitexplorer.data.repository.GitHubRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(private val repo: GitHubRepository) {
    operator fun invoke(): Flow<List<FavoriteEntity>> = repo.getFavorites()
}
