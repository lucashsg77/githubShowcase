package com.example.githubshowcase.model.data

import android.util.Log
import com.example.githubshowcase.model.repositories.RepoRepositories
import com.example.githubshowcase.model.repositories.RepoRepositoriesImpl
import com.example.githubshowcase.model.services.GitHubServices
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {

    fun load(){
        loadKoinModules(networkModules() + repositoriesModule())
    }

    private fun networkModules(): Module {
        return module{
            single {
                val interceptor = HttpLoggingInterceptor{
                    Log.e("Ok http", it)
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }
            single{
                GsonConverterFactory.create(GsonBuilder().create())
            }
            single{
                createService<GitHubServices>(get(), get())
            }
        }
    }

    private fun repositoriesModule(): Module {
        return module {
            single<RepoRepositories>{
                RepoRepositoriesImpl(get())
            }
        }
    }

    private inline fun <reified T> createService(client: OkHttpClient, factory: GsonConverterFactory): T{
        return Retrofit.Builder()
               .baseUrl("https://api.github.com/")
               .client(client)
               .addConverterFactory(factory)
               .build()
               .create(T::class.java)
    }
}