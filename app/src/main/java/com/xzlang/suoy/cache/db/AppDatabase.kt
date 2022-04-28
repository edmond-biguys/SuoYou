package com.xzlang.suoy.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.xzlang.suoy.sample.data.bean.GitHubRepo
import com.xzlang.suoy.sample.data.local.GitHubRepoDao
import com.xzlang.suoy.sample.data.local.User
import com.xzlang.suoy.sample.data.local.UserDao

/**
 *
 * How to use room database? Flow these steps.
 * 1. import the libraries in the build.gradle, add plugin id 'kotlin-kapt'
 * 2. create your entity class e.g. [User]
 * 3. create your dao class e.g. [UserDao]
 * 3. create your database class e.g. [AppDatabase]
 *
 *
 *
 *
 *
 * Created by caoj on 2022/4/1.
 */
@Database(
    entities = [User::class, GitHubRepo::class],
    version = 1,
    exportSchema = true,
//    autoMigrations = [AutoMigration(from = 1, to = 2)])
    autoMigrations = [])
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun githubRepoDao(): GitHubRepoDao

    companion object {

        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this){
                instance ?: buildDatabase(context).also {
                    instance = it
                }

            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
        }
    }
}

private const val DATABASE_NAME = "xzl_common_db"