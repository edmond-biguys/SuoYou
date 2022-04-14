package com.xinzailingtech.suoy.sample.ui

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.xinzailingtech.suoy.base.BaseActivity
import com.xinzailingtech.suoy.databinding.ActivityGithubTestBinding
import com.xzl.android.log.log
import java.lang.Exception

/**
 * Created by caoj on 2022/3/31.
 */
class GitHubTestActivity: BaseActivity() {

    private lateinit var binding: ActivityGithubTestBinding
    private lateinit var gitHubTestViewModel: GitHubTestViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        gitHubTestViewModel = ViewModelProvider(this)[GitHubTestViewModel::class.java]
        super.onCreate(savedInstanceState)
        binding = ActivityGithubTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun bindViewModel() {
        super.bindViewModel()
        gitHubTestViewModel.user.observe(this) {
            binding.textViewDesc.text = String.format("Name: %s", it.name)
        }

        gitHubTestViewModel.spinner.observe(this) { visible ->
            if (visible)
                binding.progressBarLoading.show()
            else
                binding.progressBarLoading.hide()
        }

        gitHubTestViewModel.gitHubRepos.observe(this) {
            log("gitHubTestViewModel observe repos ${it.size}")
        }
    }

    override fun setOnClickListener() {
        super.setOnClickListener()

        binding.buttonShowDialog.setOnClickListener {

            try {
                val dialog = AlertDialog.Builder(this)
                    .setPositiveButton("确定") { _, _ ->
                        log(1)
                    }
                    .setNegativeButton("取消") { _, _ ->
                        log(2)
                    }
                    .setTitle("title")
                    .setMessage("message")
                    .create()
                dialog.show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        binding.buttonUserDetail.setOnClickListener {
            gitHubTestViewModel.userId.postValue("")
        }

        binding.buttonRepos.setOnClickListener {
            gitHubTestViewModel.showReposDbData()
        }
    }

}