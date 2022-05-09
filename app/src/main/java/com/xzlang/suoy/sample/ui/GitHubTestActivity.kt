package com.xzlang.suoy.sample.ui

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.xzlang.suoy.base.BaseActivity
import com.xzlang.suoy.databinding.ActivityGithubTestBinding
import com.xzl.android.log.log
import com.xzlang.suoy.base.viewbinding.inflate
import java.lang.Exception

/**
 * Created by caoj on 2022/3/31.
 */
class GitHubTestActivity: BaseActivity() {

    private val binding: ActivityGithubTestBinding by inflate()

    private lateinit var gitHubTestViewModel: GitHubTestViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        gitHubTestViewModel = ViewModelProvider(this)[GitHubTestViewModel::class.java]
        super.onCreate(savedInstanceState)

        /*
        这个日志可以看到，0打印时间较早，1、2打印时间较晚，所以使用lazy，binding在使用时，不会因为未被初始化而报错。
         */
        log("GitHubTestActivity onCreate0")
        log("GitHubTestActivity onCreate1 $binding")
        log("GitHubTestActivity onCreate2")
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

        binding.buttonCreateIssue.setOnClickListener {
            gitHubTestViewModel.createIssue()
        }
    }

}