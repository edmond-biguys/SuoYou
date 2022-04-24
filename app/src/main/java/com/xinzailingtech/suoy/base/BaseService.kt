package com.xinzailingtech.suoy.base

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

    init {
        val okHttpClientBuilder = OkHttpClient.Builder()

        //todo Https

        //todo 添加公共param
        //添加header信息
        val commonRequestParamInterceptor = HeaderInterceptor()


        //todo 返回异常通用处理

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addInterceptor(loggingInterceptor)



        okHttpClientBuilder.addInterceptor(commonRequestParamInterceptor)

        retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(okHttpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}