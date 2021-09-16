package com.example.githubshowcase.model.repomodels

import com.google.gson.annotations.SerializedName

data class Repo(
    val id: Long,
    val name: String,
    val owner: Owner,
    @SerializedName("html_url")
    val htmlURL: String,
    val description: String,
    @SerializedName("created_at")
    val createdAt: String = "",
    @SerializedName("updated_at")
    val updatedAt: String = "",
    @SerializedName("stargazers_count")
    val stargazersCount: Long,
    val language: String? = null
)
