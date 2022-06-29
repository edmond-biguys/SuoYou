package com.xzlang.suoy.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xzl.android.log.log
import com.xzlang.suoy.databinding.ActivityTestBBinding


class TestBActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestBBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log("b create")
        binding = ActivityTestBBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

    }


    override fun onStart() {
        super.onStart()
        log("b onStart")
    }
    override fun onResume() {
        super.onResume()
        log("b onResume")
    }

    override fun onPause() {
        super.onPause()
        log("b onPause")
    }

    override fun onStop() {
        super.onStop()
        log("b onStop")
    }

    override fun onRestart() {
        super.onRestart()
        log("b onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("b onDestroy")
    }
}