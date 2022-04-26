package com.xinzailingtech.suoy.base

import com.xinzailingtech.suoy.okhttp.interceptor.ExceptionInterceptor
import com.xinzailingtech.suoy.okhttp.interceptor.HeaderInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by caoj on 2022/3/31.
 */
open class BaseService {

    protected var retrofit: Retrofit
    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    init {
        val okHttpClientBuilder = OkHttpClient.Builder()

        //todo Https 证书

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
        //添加header信息
        okHttpClientBuilder.addInterceptor(HeaderInterceptor())
        //添加返回异常通用处理
        okHttpClientBuilder.addInterceptor(ExceptionInterceptor())

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}