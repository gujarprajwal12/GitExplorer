package com.psg.gitexplorer.presentation.favorites

data class FavoritesState(
    val isLoading: Boolean = false,
    val favorites: List<com.psg.gitexplorer.data.local.FavoriteEntity> = emptyList(),
    val error: String? = null
)