package com.xzlang.suoy.test

import android.app.Application
import androidx.lifecycle.*
import com.xzlang.suoy.base.BaseViewModel
import com.xzl.android.log.log


/**
 * Created by caoj on 2022/4/6.
 */
class TestViewModel(application: Application): BaseViewModel(application) {

    val orderId = MutableLiveData<String>()

    val orderDetail = orderId.switchMap { orderId ->

        liveData {
            log(Thread.currentThread())
            emit(TestRepository.localOrderDetail(application, orderId))
            log(Thread.currentThread())
            kotlinx.coroutines.delay(3000)
            emit(TestRepository.remoteOrderDetail(orderId))
            log(Thread.currentThread())
        }

    }

    fun abc() {
//        orderId.postValue()
    }

}


data class Order(
    val orderId: String,
    val name: String,
    val price: Double,
)