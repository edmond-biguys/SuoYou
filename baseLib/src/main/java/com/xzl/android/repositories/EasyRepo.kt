package com.xzl.android.repositories

import com.xzl.android.log.log
import com.xzl.android.repositories.remote.network.EasyNet

/**
 * Created by caoj on 2022/3/23.
 */
object EasyRepo: IRepository{

    suspend fun getData() {

        //local


        //remote
        EasyNet.newInstance().testRequest()
    }

    override fun <T: Result> fetch(useCache: Boolean, local: ((localData: T) -> Unit)?, remote: (remoteData: T) -> Unit): Any {
        log("useCache $useCache")

        //get local data
        local?.invoke(Result.Local(1, "local abc") as T)

        log("fetch $remote")

        //get remote data
        //remote.invoke(Result.Local(2, "remote abc") as T)
        log("fetch remote end")
        return ""
    }
}