package com.xinzailingtech.suoy.sample.ui

import android.app.Application
import androidx.lifecycle.*
import com.xinzailingtech.suoy.base.BaseViewModel
import com.xinzailingtech.suoy.sample.data.GitHubRepository
import com.xinzailingtech.suoy.sample.data.bean.GitHubRepo
import com.xinzailingtech.suoy.sample.data.local.User
import com.xzl.android.log.log

/**
 * Created by caoj on 2022/3/31.
 */
class GitHubTestViewModel(application: Application): BaseViewModel(application) {

    val userId = MutableLiveData<String>()


    private var repo: GitHubRepository

    init {
        log("GitHub ViewModel init ${getApplication<Application>()}")
        repo = GitHubRepository(getApplication())
    }

    //获取所有数据的时候，gitHubRepos被observe时，liveData{}会自动执行
    val gitHubRepos: LiveData<List<GitHubRepo>> = liveData {

        //获取local数据
         val localRepo = repo.localRepos()
         emit(localRepo)

        //获取remote数据
        /*
         这里的执行顺序如下
         2022-04-13 14:17:22.904 ZLog -> 1 gitHubRepos Thread[main,5,main]
         2022-04-13 14:17:22.905 ZLog -> 2 in remoteRepos Thread[DefaultDispatcher-worker-1,5,main]
         2022-04-13 14:17:24.179 ZLog -> 3 after network Thread[DefaultDispatcher-worker-1,5,main]
         2022-04-13 14:17:24.186 ZLog -> 4 gitHubRepos Thread[main,5,main]
         1、4 在UI线程，2、3在异步线程，但整体代码看着是顺序执行，UI线程不会被卡住，使用协程，方便阅读。
         */
        log("1 gitHubRepos ${Thread.currentThread()}")
        val repos = repo.remoteRepos()
        log("4 gitHubRepos ${Thread.currentThread()}")
        emit(repos)
        log("gitHubRepos $repos")
    }

    //通过id 获取 object
    val user: LiveData<User> = userId.switchMap { id ->
        liveData {
            log("post id is $id")
            //获取local数据
            // val localUser = repo.localUser(id)
            // emit(localUser)
            emit(User(name = "local name", age = 10))

            //获取remote数据
            // val remoteUser = repo.remoteUser(id)
            // emit(remoteUser)
            emit(User(name = "remote name", age = 11))
        }
    }



    /*fun fetchAllUser() {
        launchDataLoad {

            val users = repo.allLocalUsers()
            log("users from db $users")
            if (users.isNotEmpty()) {
                _user.postValue(users[0])
            }

            val repo = repo.remoteRepos()
            log("repo $repo")
        }
    }*/
}