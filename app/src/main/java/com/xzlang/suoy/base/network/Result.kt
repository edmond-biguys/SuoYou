package com.xzlang.suoy.base.network

/**
 * Created by caoj on 2022/4/25.
 */
sealed class Result<out R> {
    data class Success<out T>(val data: T): Result<T>()
    data class Error(val exception: Exception): Result<Nothing>()
}