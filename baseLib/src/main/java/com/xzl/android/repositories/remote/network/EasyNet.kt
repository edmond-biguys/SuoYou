package com.xzl.android.repositories.remote.network

import com.xzl.android.log.log
import com.xzl.android.repositories.remote.network.test.EasyNetTest
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


/**
 * Created by caoj on 2022/3/21.
 */
class EasyNet {

    suspend fun testRequest() {

        EasyNetTest.testRequest()
    }

    private fun addOkHttpLogInterceptor(builder: OkHttpClient.Builder) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(httpLoggingInterceptor)
    }


    companion object {
        fun newInstance(): EasyNet {
            return EasyNet()
        }
    }



}

fun main(args: Array<String>) {
//    CoroutineScope(Dispatchers.IO).launch {
//        EasyNet.newInstance().testRequest()
//    }
    log("1 " + Thread.currentThread())
    runBlocking {
        log("2 " + Thread.currentThread())
        EasyNet.newInstance().testRequest()
    }
//    EasyNet.newInstance().testOkHttp()
}