package com.xzl.android.log

import android.util.Log
import java.util.*

/**
 * Created by caoj on 2022/3/22.
 */
//object ZLog {
//    fun log(message: Any?) {
//        println("${Date()} ZLog -> $message")
//    }
//}

fun log(message: Any?) {
    println("${now24()} ZLog -> $message")
//    Log.i("ZLog", "ZLog -> $message")
}
fun loge(message: Any?) {
    Log.e("ZLog", "ZLog -> $message")
}

fun now24(): String{
    var result: String
    with(Calendar.getInstance()) {
        val year = get(Calendar.YEAR)
        val month = get(Calendar.MONTH) + 1
        val day = get(Calendar.DAY_OF_MONTH)
        val hour = get(Calendar.HOUR_OF_DAY)
        val minute = get(Calendar.MINUTE)
        val second = get(Calendar.SECOND)
        result = String.format("%d-%d-%d %d:%d:%d", year, month, day, hour, minute, second)
    }
    return result
}