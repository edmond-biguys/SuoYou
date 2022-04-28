package com.xzlang.suoy

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.xzlang.suoy.base.BaseActivity
import com.xzlang.suoy.databinding.ActivityMainBinding
import com.xzlang.suoy.sample.ui.GitHubTestActivity
import com.xzlang.suoy.test.TestActivity
import com.xzl.android.log.log

class MainActivity : BaseActivity() {

//    private val viewModel by viewModels<MainActivityViewModel>()
    private lateinit var viewModel: MainActivityViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun bindViewModel() {
        log("bindViewModel")
        viewModel.liveData.observe(this) {
            log("liveData callback $it ${Thread.currentThread()}")
        }
    }

    override fun setOnClickListener() {
        log("setOnClickListener")
        binding.buttonToGitHubTest.setOnClickListener {
            startActivity(Intent(this, GitHubTestActivity::class.java))
        }

        binding.buttonToTestView.setOnClickListener {
            startActivity(Intent(this, TestActivity::class.java))
        }
    }
}