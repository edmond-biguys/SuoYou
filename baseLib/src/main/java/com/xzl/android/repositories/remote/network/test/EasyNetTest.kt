package com.xzl.android.repositories.remote.network.test

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.xzl.android.log.log
import com.xzl.android.repositories.remote.network.GitHubService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by caoj on 2022/3/22.
 */
object EasyNetTest {


    suspend fun testRequest() {

        val okHttpClientBuilder = OkHttpClient.Builder()

        //打印http请求日志
        //addOkHttpLogInterceptor(okHttpClientBuilder)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(okHttpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GitHubService::class.java)

        log("start")

        withContext(Dispatchers.Main) {
            log("a Dispatchers.Main ${Thread.currentThread()}")
        }
        log("a end")
        withContext(Dispatchers.IO) {
            log("b Dispatchers.IO ${Thread.currentThread()}")
        }
        log("b end")
        withContext(Dispatchers.Main) {
            log("c Dispatchers.Main ${Thread.currentThread()}")
        }
        log("c end")
        withContext(Dispatchers.Default) {
            log("d Dispatchers.Default ${Thread.currentThread()}")
        }
        log("d end")
        //withContext(Dispatchers.IO) {
        log("3 " + Thread.currentThread())
        val result = service.listRepos("edmond-biguys").execute()
        log(result.isSuccessful)
        log(result.code())
        log(result.message())
        log("4 " + Thread.currentThread())
        //}
        log("5 " + Thread.currentThread())
        log("end")

    }


    fun testOkHttp() {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.github.com/users/edmond-biguys/repos")
            .build()
        Thread {
            val response = okHttpClient.newCall(request).execute()
            log("${response.code}")
            log(response.message)
            val result = response.body?.string()
//            val moshi = Moshi.Builder().build()
//            val type = Types.newParameterizedType(List::class.java, Repo::class.java)
//            val jsonAdapter = moshi.adapter<List<Repo>>(type)
//            val bean = jsonAdapter.fromJson(result)
//            log(bean == null)
//            log(bean?.size)

            parseByGson(result!!)


        }.start()
    }

    private fun parseByGson(content: String) {
        val type = object : TypeToken<ArrayList<Repo?>?>() {}.type
        val repo = Gson().fromJson<List<Repo>>(content, type)
        log(repo.size)
        log(repo[0].full_name)
    }

}

fun main(args: Array<String>) {
    println("EasyNetTest")
}