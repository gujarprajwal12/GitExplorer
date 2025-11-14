package com.psg.gitexplorer.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psg.gitexplorer.domain.usecase.GetFavoritesUseCase
import com.psg.gitexplorer.domain.usecase.SearchRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchRepositoriesUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state.asStateFlow()

    private val intents = MutableSharedFlow<SearchIntent>()

    init {
        observeIntents()
        observeFavorites()
    }

    fun processIntent(intent: SearchIntent) {
        viewModelScope.launch { intents.emit(intent) }
    }

    private fun observeIntents() {
        viewModelScope.launch {
            intents.collect { intent ->
                when (intent) {
                    is SearchIntent.Search -> {
                        _state.update { it.copy(isLoading = true, error = null) }
                        try {
                            val list = searchUseCase(intent.query)
                            _state.update { it.copy(isLoading = false, repos = list) }
                        } catch (e: Exception) {
                            _state.update { it.copy(isLoading = false, error = e.message ?: "Error") }
                        }
                    }
                }
            }
        }
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            getFavoritesUseCase().collect { favs ->
                _state.update { it.copy(favorites = favs) }
            }
        }
    }
}
