package com.example.githubshowcase.model.services

import com.example.githubshowcase.model.repomodels.Repo
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubServices {
    @GET("users/{user}/repos")
    suspend fun listRepo(@Path("user") user: String): List<Repo>
}