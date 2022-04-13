package com.xinzailingtech.suoy.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xzl.android.log.log
import com.xzl.android.log.loge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by caoj on 2022/3/31.
 */
open class BaseViewModel(application: Application): AndroidViewModel(application) {

    private val _spinner = MutableLiveData<Boolean>()
    val spinner: LiveData<Boolean>
        get() = _spinner
    fun launchDataLoad(block: suspend ()-> Unit): Job {
        log("2")
        return viewModelScope.launch(Dispatchers.IO) {
            try {
                _spinner.postValue(true)
                log("3 ${Thread.currentThread()}")
                //start loading
                block()
            } catch (error: Throwable) {
                loge("4 ${error.message}")
                _spinner.postValue(false)
                //show error
            } finally {
                log("5")
                _spinner.postValue(false)
                //end loading
            }
        }
    }

}