package com.xinzailingtech.suoy.test

import android.content.Context
import com.xinzailingtech.suoy.cache.db.AppDatabase
import com.xzl.android.log.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by caoj on 2022/4/6.
 */
object TestRepository {

    suspend fun remoteOrderDetail(orderId: String): Order = withContext(Dispatchers.Default) {
        log("remoteOrderDetail ${Thread.currentThread()}")
        if (orderId == "id1") {
            Order("id1", "空调", 2201.0)
        } else {
            Order("id2", "空调", 2202.0)
        }
    }

    suspend fun localOrderDetail(context: Context, orderId: String): Order = withContext(Dispatchers.IO){
        val user = AppDatabase.getInstance(context).userDao().getAll()
        log("localOrderDetail user $user ${Thread.currentThread()}")
        if (orderId == "id1") {
            Order("id1", "热水器", 1001.0)
        } else {
            Order("id2", "热水器", 1002.0)
        }
    }
}