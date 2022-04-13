package com.xzl.android.repositories

/**
 * Created by caoj on 2022/3/25.
 */
sealed class Result {
    data class Local(val id: Int ,val name: String): Result()
    data class Remote(val id: Int ,val name: String): Result()
}
