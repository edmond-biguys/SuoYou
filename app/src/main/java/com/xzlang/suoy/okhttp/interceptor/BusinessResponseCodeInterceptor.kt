package com.xzlang.suoy.okhttp.interceptor

import com.xzl.android.log.log
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by caoj on 2022/4/28.
 */
class BusinessResponseCodeInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val response = chain.proceed(chain.request())

            /*
            这里需要解析，返回数据中业务code，
            {
		        "code": 10012,
		        "message": "this is message",
		        "data": {}
		    }
             */
            val body = response.body
            log(body)
            val code = 123 //通过body解析出code
            dispatchCode(code)
            return response
        } catch (e: Exception) {
            log(e)
            throw e
        }
    }

    private fun dispatchCode(code: Int) {
        when(code) {
            0 -> {
                //do something
            }
            1 -> {
                /*
                do something
                比如，数据异常、参数异常等；
                token无效、过期等
                 */

            }
            else -> {
                //do something
            }
        }
    }
}