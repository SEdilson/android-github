package com.sedilson.android_github.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sedilson.android_github.R
import com.sedilson.android_github.retrofit.webclient.RepositoryWebClient
import com.sedilson.android_github.ui.activity.extensions.showErrorMessage
import com.sedilson.android_github.ui.recyclerview.adapter.RepositoryListAdapter
import com.sedilson.android_github.utils.InfiniteScrollListener
import kotlinx.android.synthetic.main.activity_repo_list.*

class RepositoryListActivity : AppCompatActivity() {

    private var currentPage = 1

    private val adapter by lazy {
        RepositoryListAdapter(context = this)
    }
    private val webClient by lazy {
        RepositoryWebClient(page = currentPage)
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
        val layoutManager = activity_repo_list_recyclerview.layoutManager as LinearLayoutManager
        activity_repo_list_recyclerview.adapter = adapter
        activity_repo_list_recyclerview.apply {
            addOnScrollListener(InfiniteScrollListener({
                currentPage++
                searchRepos()
            }, layoutManager))
        }
    }

    private fun searchRepos() {
        webClient.searchForAllRepos(
            whenSuccess = {repositories ->
                val listRepos = repositories?.items
                adapter.updateRepoList(listRepos)
            }, whenFailed = {
                showErrorMessage("Unable to load the repos")
            }
        )
    }
}