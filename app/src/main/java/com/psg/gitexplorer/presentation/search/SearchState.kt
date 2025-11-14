package com.psg.gitexplorer.presentation.search

import com.psg.gitexplorer.data.model.Repository
import com.psg.gitexplorer.data.local.FavoriteEntity

data class SearchState(
    val isLoading: Boolean = false,
    val repos: List<Repository> = emptyList(),
    val error: String? = null,
    val favorites: List<FavoriteEntity> = emptyList()
)
