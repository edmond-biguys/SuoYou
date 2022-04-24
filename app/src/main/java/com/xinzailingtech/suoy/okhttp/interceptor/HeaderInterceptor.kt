package com.xinzailingtech.suoy.okhttp.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * 在请求中加入公共的头部信息，如token等
 * Created by caoj on 2022/4/20.
 */
class HeaderInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val requestBuilder = request.newBuilder()
        requestBuilder.addHeader("Accept", "application/json")
        requestBuilder.addHeader("Content-type", "application/json")
//        //如果已经登录，add token
//        if (loginEnable) {
//            requestBuilder.addHeader("token", "token")
//        }
        return chain.proceed(requestBuilder.build())
    }
}