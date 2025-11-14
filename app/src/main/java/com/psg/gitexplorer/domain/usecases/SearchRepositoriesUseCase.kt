package com.psg.gitexplorer.domain.usecase

import com.psg.gitexplorer.data.repository.GitHubRepository
import com.psg.gitexplorer.data.model.Repository
import javax.inject.Inject

class SearchRepositoriesUseCase @Inject constructor(private val repo: GitHubRepository) {
    suspend operator fun invoke(query: String): List<Repository> = repo.searchRepositories(query)
}
