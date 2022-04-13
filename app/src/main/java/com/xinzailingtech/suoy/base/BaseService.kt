package com.xinzailingtech.suoy.base

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by caoj on 2022/3/31.
 */
open class BaseService {

    protected var retrofit: Retrofit

    init {
        val okHttpClientBuilder = OkHttpClient.Builder()
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(okHttpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}