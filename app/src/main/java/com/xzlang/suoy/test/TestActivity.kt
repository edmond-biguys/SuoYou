package com.xzlang.suoy.test

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.xzlang.suoy.base.BaseActivity
import com.xzlang.suoy.databinding.ActivityTestBinding
import com.xzl.android.log.log
import com.xzlang.suoy.MainActivity

/**
 * Created by caoj on 2022/4/6.
 */
class TestActivity: BaseActivity() {
    private lateinit var binding: ActivityTestBinding

    private lateinit var viewModel: TestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        log("onCreate")
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

        binding.button3.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("title")
                .setMessage("message")
                .setPositiveButton("confirm") { _, _ ->
                    log("confirm")
                }
                .setNegativeButton("cancel") {
                    _, _ ->
                    log("cancel")
                }
                .show()
        }

        binding.button4.setOnClickListener {
            startActivity(Intent(this, TestBActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        log("onStart")
    }
    override fun onResume() {
        super.onResume()
        log("onResume")
    }

    override fun onPause() {
        super.onPause()
        log("onPause")
    }

    override fun onStop() {
        super.onStop()
        log("onStop")
    }

    override fun onRestart() {
        super.onRestart()
        log("onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }
}