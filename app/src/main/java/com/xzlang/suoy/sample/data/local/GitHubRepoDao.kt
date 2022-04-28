package com.xzlang.suoy.sample.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xzlang.suoy.sample.data.bean.GitHubRepo

/**
 * Created by caoj on 2022/4/7.
 */
@Dao
interface GitHubRepoDao {
    @Query("SELECT * FROM github_repo")
    fun getAll(): List<GitHubRepo>

    @Query("SELECT * FROM github_repo WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<GitHubRepo>
//
    @Query("SELECT * FROM github_repo WHERE full_name = :full_name")
    fun findByName(full_name: String): List<GitHubRepo>
//
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg repos: GitHubRepo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll2(repos: List<GitHubRepo>)

}