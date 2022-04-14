package com.xinzailingtech.suoy.sample.data.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by caoj on 2022/4/7.
 */
@Entity(tableName = "github_repo")
data class GitHubRepo(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,

    var full_name: String,

    var node_id: String,

    @ColumnInfo(name = "private")
    @SerializedName("private")
    //github的这个字段名称把自己坑了，private是关键字
    var _private: Boolean,
//    @ColumnInfo(defaultValue = "")
//    var name: String,
    )

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,
    val name: String,
    val age: Int,
)
