package com.sedilson.android_github.retrofit.webclient

import com.sedilson.android_github.models.Repository
import com.sedilson.android_github.models.RepositoryResponse
import com.sedilson.android_github.retrofit.AppRetrofit
import com.sedilson.android_github.retrofit.service.RepositoryService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val FAILED_REQUEST_MESSAGE = "Request to API failed"

class RepositoryWebClient(
    private val service: RepositoryService = AppRetrofit().repositoryService,
    private val page: Int
) {

    private fun <T> executeRequest(
        call: Call<T>,
        whenSuccess: (repositories: T?) -> Unit,
        whenFailed: (error: String?) -> Unit
    ) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if(response.isSuccessful) {
                    whenSuccess(response.body())
                } else {
                    whenFailed(FAILED_REQUEST_MESSAGE)
                }
            }
            override fun onFailure(call: Call<T>, t: Throwable) {
                whenFailed(t.message)
            }
        })
    }

    fun searchForAllRepos(
        whenSuccess: (retrievedRepos: RepositoryResponse?) -> Unit,
        whenFailed: (error: String?) -> Unit
    ) {
        executeRequest(
            service.getAllRepos(page),
            whenSuccess,
            whenFailed
        )
    }
}