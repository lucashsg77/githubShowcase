package com.example.githubshowcase.model.data

import com.example.githubshowcase.model.usecases.ListUserReposUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    fun load(){
        loadKoinModules(useCaseModule())
    }

    private fun useCaseModule(): Module {
        return module {
            factory{ ListUserReposUseCase(get()) }
        }
    }

}