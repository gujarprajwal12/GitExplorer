package com.psg.gitexplorer.domain.usecase

import com.psg.gitexplorer.data.repository.GitHubRepository
import javax.inject.Inject

class IsFavoriteUseCase @Inject constructor(private val repo: GitHubRepository) {
    suspend operator fun invoke(id: Long): Boolean = repo.isFavorite(id)
}
