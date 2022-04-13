package com.xinzailingtech.suoy.sample.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by caoj on 2022/4/1.
 */
@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,
    val name: String,
    val age: Int,
)
