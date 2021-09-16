package com.example.githubshowcase.view


import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubshowcase.R
import com.example.githubshowcase.databinding.ItemRepoBinding
import com.example.githubshowcase.model.repomodels.Repo
import com.example.githubshowcase.utils.format
import java.text.SimpleDateFormat
import java.util.*

class RepoListAdapter :  ListAdapter<Repo, RepoListAdapter.ViewHolder>(DiffCallback()){

    inner class ViewHolder(private val binding: ItemRepoBinding): RecyclerView.ViewHolder(binding.root){
        private var currentItem: Repo? = null
        private var currentPosition: Int = 0
        init{
            binding.openBrowser.setOnClickListener {
                val defaultBrowser = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
                defaultBrowser.data = Uri.parse(getItem(currentPosition).htmlURL)
                startActivity(this.itemView.context, defaultBrowser, null)
            }
        }
        @SuppressLint("SetTextI18n" , "SimpleDateFormat")
        fun bind(item: Repo, position: Int){
            binding.repoNameTv.text = item.name
            binding.repoDescriptionTv.text = item.description
            binding.repoLanguage.text = item.language
            binding.repoStars.text = item.stargazersCount.toString()
            var date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(item.updatedAt)
            binding.lastUpdate.text = "${ binding.root.context.getString(R.string.lastUpdate) } ${date?.format()}"
            date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(item.createdAt)
            binding.createdAt.text = "${ binding.root.context.getString(R.string.createdAt) } ${date?.format()}"
            Glide.with(binding.root.context).load(item.owner.avatarURL).into(binding.ownerIv)
            this.currentItem = item
            this.currentPosition = position
            chipColor()
        }

        private fun chipColor() {
            when (this.itemView.context.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {
                Configuration.UI_MODE_NIGHT_YES -> {
                    binding.repoStars.chipBackgroundColor =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                this.itemView.context ,
                                R.color.blackChip
                            )
                        )
                }
                Configuration.UI_MODE_NIGHT_NO -> {
                    binding.repoStars.chipBackgroundColor =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                this.itemView.context ,
                                R.color.white
                            )
                        )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRepoBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder , position: Int) {
        holder.bind(getItem(position), position)
    }
}