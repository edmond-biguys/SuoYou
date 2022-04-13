package com.xzl.android.repositories.remote.network


import com.xzl.android.repositories.remote.network.test.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by caoj on 2022/3/21.
 */
interface GitHubService {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<Repo>>
}