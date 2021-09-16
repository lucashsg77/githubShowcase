package com.example.githubshowcase.view

import androidx.recyclerview.widget.DiffUtil
import com.example.githubshowcase.model.repomodels.Repo

class DiffCallback: DiffUtil.ItemCallback<Repo>() {
    override fun areItemsTheSame(oldItem: Repo , newItem: Repo): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Repo , newItem: Repo): Boolean = oldItem.id == newItem.id
}