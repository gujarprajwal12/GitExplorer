package com.psg.gitexplorer.data.model

data class SearchResponse(
    val total_count: Int,
    val items: List<Repository>
)
