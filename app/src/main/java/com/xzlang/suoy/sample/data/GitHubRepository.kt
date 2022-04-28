package com.xzlang.suoy.sample.data

import android.app.Application
import com.xzlang.suoy.base.BaseRepository
import com.xzlang.suoy.base.network.Result
import com.xzlang.suoy.cache.db.AppDatabase
import com.xzlang.suoy.sample.data.bean.GitHubRepo
import com.xzlang.suoy.sample.data.remote.GitHubService
import com.xzl.android.log.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by caoj on 2022/3/31.
 */
class GitHubRepository(application: Application): BaseRepository() {

    private val githubRepoDao = AppDatabase.getInstance(application).githubRepoDao()

    private val gitHubService = GitHubService()

    suspend fun localRepos(): List<GitHubRepo> = withContext(Dispatchers.IO) {
        githubRepoDao.getAll()
    }

    suspend fun remoteRepos(): Result<List<GitHubRepo>> = withContext(Dispatchers.IO) {
        log("2 in remoteRepos ${Thread.currentThread()}")
        try {
            val repos = gitHubService.allRepos()
            log("3 after network ${Thread.currentThread()}")
            insertAll(repos)
            Result.Success(repos)
        } catch (e: Exception) {
            log("remoteRepos exception ${e.message}")
            Result.Error(e)
        }
    }

    suspend fun createIssue(): Result<Boolean> = withContext(Dispatchers.IO){
        try {
            gitHubService.createIssue()
            Result.Success(true)
        } catch (e: Exception) {
            log("createIssue exception ${e.message}")
            Result.Error(e)
        }
    }

    suspend fun insertAll(repos: List<GitHubRepo>) = withContext(Dispatchers.IO) {
       githubRepoDao.insertAll2(repos)
    }
}