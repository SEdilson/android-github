package com.sedilson.android_github.retrofit.service

import com.sedilson.android_github.models.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryService {

    @GET("/search/repositories?q=language:Java&sort=stars&page=1")
    fun getAllRepos(): Call<List<Repository>>
}