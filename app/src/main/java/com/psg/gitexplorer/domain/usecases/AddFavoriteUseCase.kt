package com.psg.gitexplorer.domain.usecase

import com.psg.gitexplorer.data.local.FavoriteEntity
import com.psg.gitexplorer.data.repository.GitHubRepository
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(private val repo: GitHubRepository) {
    suspend operator fun invoke(entity: FavoriteEntity) = repo.addFavorite(entity)
}
