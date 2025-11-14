package com.psg.gitexplorer.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psg.gitexplorer.data.local.FavoriteEntity
import com.psg.gitexplorer.domain.usecase.AddFavoriteUseCase
import com.psg.gitexplorer.domain.usecase.IsFavoriteUseCase
import com.psg.gitexplorer.domain.usecase.RemoveFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DetailsState())
    val state: StateFlow<DetailsState> = _state

    fun setInitial(repoId: Long) {
        viewModelScope.launch {
            val fav = isFavoriteUseCase(repoId)
            _state.update { it.copy(isFavorite = fav) }
        }
    }

    fun toggleFavorite(favoriteEntity: FavoriteEntity) {
        viewModelScope.launch {
            try {
                val exists = isFavoriteUseCase(favoriteEntity.id)
                if (exists) {
                    removeFavoriteUseCase(favoriteEntity)
                    _state.update { it.copy(isFavorite = false) }
                } else {
                    addFavoriteUseCase(favoriteEntity)
                    _state.update { it.copy(isFavorite = true) }
                }
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message) }
            }
        }
    }
}
