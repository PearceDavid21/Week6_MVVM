package com.example.week6_daily2_mvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.week6_daily2_mvvm.model.Commits
import com.example.week6_daily2_mvvm.model.Repository
import com.example.week6_daily2_mvvm.network.GitFactory
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class GitViewModel : ViewModel() {

    private val gitFactory: GitFactory = GitFactory()

    fun getRepositories(userName: String): Observable<List<Repository>> {

        return gitFactory.getRpositories(userName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())


    }

    fun getRepoCommits(userName: String, repoName: String): Observable<List<Commits>> {
        return gitFactory.getRepoCommit(userName, repoName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    }

}