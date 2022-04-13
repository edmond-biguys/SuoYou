package com.xzl.android.repositories


/**
 * Created by caoj on 2022/3/23.
 */
interface IRepository {
    fun <T: Result> fetch(userCache: Boolean = false, local: ((any: T) -> Unit)? = null, remote: (any: T )->Unit): Any

//    fun save()

//    fun delete()

//    fun update()

//    fun clear()


}