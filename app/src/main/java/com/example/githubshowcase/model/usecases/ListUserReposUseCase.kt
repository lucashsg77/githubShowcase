package com.example.githubshowcase.model.usecases

import com.example.githubshowcase.model.repomodels.Repo
import com.example.githubshowcase.model.repositories.RepoRepositories
import kotlinx.coroutines.flow.Flow

class ListUserReposUseCase(private val repository: RepoRepositories)
    : UseCase<String, List<Repo>>() {

    override suspend fun execute(param: String): Flow<List<Repo>> {
        return repository.repositories(param)
    }
}