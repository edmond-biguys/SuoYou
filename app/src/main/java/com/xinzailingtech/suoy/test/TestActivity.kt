package com.xinzailingtech.suoy.test

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.xinzailingtech.suoy.base.BaseActivity
import com.xinzailingtech.suoy.databinding.ActivityTestBinding
import com.xzl.android.log.log

/**
 * Created by caoj on 2022/4/6.
 */
class TestActivity: BaseActivity() {
    private lateinit var binding: ActivityTestBinding

    private lateinit var viewModel: TestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(this)[TestViewModel::class.java]
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Thread.sleep(6000)
        log(Thread.currentThread())
//        Thread {
//            var index = 0
//            while (true) {
//                Thread.sleep(2000)
//
//                runOnUiThread {
//                    binding.textViewOrder.text = "index $index"
//                }
//                index++
//            }
//        }.start()

    }

    override fun bindViewModel() {
        super.bindViewModel()
        viewModel.orderDetail.observe(this) {
            log("observe orderDetail ${Thread.currentThread()}")
            binding.textViewOrder.text = "OrderId: ${it.orderId} name: ${it.name} price: ${it.price}"
        }
    }

    override fun setOnClickListener() {
        super.setOnClickListener()
        binding.buttonOrder1.setOnClickListener {
            viewModel.orderId.postValue("id1")
        }

        binding.buttonOrder2.setOnClickListener {
            viewModel.orderId.postValue("id2")
        }
    }
}