package com.xinzailingtech.suoy.sample.data.remote

import com.xinzailingtech.suoy.base.BaseService
import com.xinzailingtech.suoy.sample.data.bean.GitHubRepo
import com.xinzailingtech.suoy.sample.data.bean.Issue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
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

    suspend fun createIssue() = withContext(Dispatchers.Default) {
        gitHubService.createIssue(
            "edmond-biguys", "test-repo",
            Issue("issue title 01", "issue body 01"))
    }
}

interface IGitHubService {
    @GET("users/{user}/repos")
    suspend fun listRepos(@Path("user") user: String): List<GitHubRepo>

    @POST("repos/{user}/{repo}/issues")
//    suspend fun createIssue(@Path("user") user: String, @Path("repo") repo: String): List<Any>
    suspend fun createIssue(@Path("user") user: String, @Path("repo") repo: String, @Body req: Issue):List<Any>

}