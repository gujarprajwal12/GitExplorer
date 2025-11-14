package com.psg.gitexplorer.presentation.search

sealed class SearchIntent {
    data class Search(val query: String): SearchIntent()
}
