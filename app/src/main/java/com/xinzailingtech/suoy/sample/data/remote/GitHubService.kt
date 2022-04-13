package com.xinzailingtech.suoy.sample.data.remote

import com.xinzailingtech.suoy.base.BaseService
import com.xinzailingtech.suoy.sample.data.bean.GitHubRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by caoj on 2022/3/31.
 */
class GitHubService: BaseService() {

    private val gitHubService = retrofit.create(IGitHubService::class.java)

    suspend fun allRepos(): List<GitHubRepo> = withContext(Dispatchers.Default) {
        val result = gitHubService.listRepos("edmond-biguys")
        result
    }
}

interface IGitHubService {
    @GET("users/{user}/repos")
    suspend fun listRepos(@Path("user") user: String): List<GitHubRepo>
}