package com.xzlang.suoy.base

import android.app.Application

/**
 * Created by caoj on 2022/4/25.
 */
class App: Application() {


    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: App? = null
            private set
    }

}