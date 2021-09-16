package com.example.githubshowcase.model.repositories

import android.os.RemoteException
import com.example.githubshowcase.model.services.GitHubServices
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class RepoRepositoriesImpl(private val services: GitHubServices) : RepoRepositories {

    override suspend fun repositories(user: String) = flow{
        try{
            emit(services.listRepo(user).sortedWith(compareBy({it.language}, {it.createdAt})))
        }catch(ex: HttpException){
            throw RemoteException(ex.message)
        }
    }
}