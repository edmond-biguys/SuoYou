package com.xzlang.suoy.base

import android.os.Bundle
import androidx.activity.ComponentActivity

/**
 * Created by caoj on 2022/3/23.
 */
abstract class BaseActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindViewModel()
    }

    override fun onStart() {
        super.onStart()
        setOnClickListener()
    }

    open fun bindViewModel(){}

    open fun setOnClickListener() {}

}
