package com.xzlang.suoy.base.viewbinding

import android.view.LayoutInflater
import androidx.activity.ComponentActivity
import androidx.viewbinding.ViewBinding
import com.xzl.android.log.log

/**
 * Created by caoj on 2022/5/7.
 */
inline fun <reified VB: ViewBinding> ComponentActivity.inflate() = lazy {
    log("ComponentActivity.inflate")
    inflateBinding<VB>(layoutInflater).apply {
        setContentView(root)
    }
}

inline fun <reified VB: ViewBinding> inflateBinding(layoutInflater: LayoutInflater) =
    VB::class.java.getMethod("inflate", LayoutInflater::class.java).invoke(null, layoutInflater) as VB
