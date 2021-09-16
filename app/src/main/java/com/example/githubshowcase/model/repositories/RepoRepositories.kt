package com.example.githubshowcase.model.repositories

import com.example.githubshowcase.model.repomodels.Repo
import kotlinx.coroutines.flow.Flow

interface RepoRepositories {
    suspend fun repositories(user: String): Flow<List<Repo>>
}