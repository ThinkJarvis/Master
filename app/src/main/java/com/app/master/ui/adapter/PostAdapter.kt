package com.app.master.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.master.databinding.PostListItemBinding
import com.app.master.mode.Post
import com.app.master.ui.list.ListViewModel

class PostAdapter(private val listViewModel: ListViewModel) : ListAdapter<Post, PostAdapter.ListViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(listViewModel, item)
    }

    class ListViewHolder private constructor(private val binding: PostListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: ListViewModel, post: Post) {
            binding.viewModel = viewModel
            binding.postEvent = post
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PostListItemBinding.inflate(layoutInflater, parent, false)
                return ListViewHolder(binding)
            }
        }

    }
}

class TaskDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.title == newItem.title
    }
}