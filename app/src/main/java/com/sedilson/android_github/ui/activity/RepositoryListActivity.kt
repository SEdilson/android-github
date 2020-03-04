package com.sedilson.android_github.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sedilson.android_github.R
import com.sedilson.android_github.retrofit.webclient.RepositoryWebClient
import com.sedilson.android_github.ui.activity.extensions.showErrorMessage
import com.sedilson.android_github.ui.recyclerview.adapter.RepositoryListAdapter
import kotlinx.android.synthetic.main.activity_repo_list.*

class RepositoryListActivity : AppCompatActivity() {

    private val adapter by lazy {
        RepositoryListAdapter(context = this)
    }
    private val webClient by lazy {
        RepositoryWebClient()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)
        configureRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        searchRepos()
    }

    private fun configureRecyclerView() {
        activity_repo_list_recyclerview.adapter = adapter
    }

    private fun searchRepos() {
        webClient.searchForAllRepos(
            whenSuccess = {repositories ->
                repositories?.let { adapter.updateRepoList(it) }
            }, whenFailed = {
                showErrorMessage("Unable to get the repos")
            }
        )
    }
}
