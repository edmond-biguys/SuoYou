package com.xinzailingtech.suoy.sample.data.bean

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
/**
 * Created by caoj on 2022/4/7.
 */
@Entity(tableName = "github_repo")
data class GitHubRepo(
//    @PrimaryKey(autoGenerate = true) val Ida: Int = 0,
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var node_id: String,
    var full_name: String,
    var private: Boolean

    )/*: Serializable*/ {
    constructor(): this(0, "", "", false)
}

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,
    val name: String,
    val age: Int,
)
