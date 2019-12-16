package com.example.gitmvvmapp.view


import com.example.week6_daily2_mvvm.model.Commits
import com.example.week6_daily2_mvvm.model.Repository
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GitService {

    @GET("users/{user_name}/repos")
    fun getUser(@Path("user_name") username: String): Observable<List<Repository>>


    @GET("repos/{user_name}/{repo_name}/commits")
    fun getRepoCommit(
        @Path("user_name") username: String,
        @Path("repo_name") repo_name: String
    ): Observable<List<Commits>>
}

// https://api.github.com/repos/{user name}/{repo name}/commits