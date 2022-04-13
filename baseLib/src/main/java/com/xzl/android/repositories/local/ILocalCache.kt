package com.xzl.android.repositories.local

/**
 * Created by caoj on 2022/3/24.
 */
interface ILocalCache {

    fun save()

    fun delete()

    fun update()

    fun fetch()

    fun clear()

}