package com.sedilson.android_github.retrofit.service

import com.sedilson.android_github.models.RepositoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryService {

    @GET("/search/repositories?q=language:Java&sort=stars")
    fun getAllRepos(@Query("page") page: Int): Call<RepositoryResponse>
}