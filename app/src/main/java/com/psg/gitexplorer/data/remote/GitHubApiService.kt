package com.psg.gitexplorer.data.remote



import com.psg.gitexplorer.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApiService {
    @GET("search/repositories")
    suspend fun searchRepositories(@Query("q") query: String): SearchResponse
}
