package com.sedilson.android_github.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sedilson.android_github.R
import com.sedilson.android_github.extensions.setStringMaxLength
import com.sedilson.android_github.models.Repository
import com.sedilson.android_github.models.RepositoryResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.repo_item.view.*
import retrofit2.Response

class RepositoryListAdapter(
    private val context: Context,
    private val repositories: MutableList<Repository> = mutableListOf()
) : RecyclerView.Adapter<RepositoryListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val createdView = LayoutInflater.from(context)
            .inflate(R.layout.repo_item, parent, false)
        return ViewHolder(createdView)
    }

    override fun getItemCount() = repositories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val retrievedRepository = repositories[position]
        holder.bindRepo(retrievedRepository)
    }

    fun updateRepoList(repositories: List<Repository>?) {
        this.repositories.clear()
        repositories?.let { this.repositories.addAll(it) }
        notifyItemRangeInserted(0, this.repositories.size)
    }

    inner class ViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

        private val repositoryNameTextView = itemView.repo_item_name
        private val repositoryDescriptionTextView = itemView.repo_item_description
        private val repositoryNumberOfStarsTextView = itemView.repo_item_number_of_stars
        private val repositoryNumberOfForksTextView = itemView.repo_item_number_of_forks
        private val repositoryOwnerUsernameTextView = itemView.repo_item_user_name
        private val repositoryOwnerAvatarImageView = itemView.repo_item_user_avatar

        fun bindRepo(repository: Repository) {
            repositoryNameTextView?.text = repository.name.setStringMaxLength(12)
            repositoryDescriptionTextView?.text = repository.description.setStringMaxLength(60)
            repositoryNumberOfForksTextView?.text = repository.forks_count.toString()
            repositoryNumberOfStarsTextView?.text = repository.stargazers_count.toString()
            repositoryOwnerUsernameTextView?.text = repository.owner.login
            Picasso.with(context).load(repository.owner.avatar_url)
                .into(repositoryOwnerAvatarImageView)
        }
    }
}