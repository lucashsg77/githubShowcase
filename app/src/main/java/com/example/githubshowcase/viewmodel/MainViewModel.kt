package com.example.githubshowcase.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubshowcase.model.repomodels.Repo
import com.example.githubshowcase.model.usecases.ListUserReposUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(private val listUserReposUseCase: ListUserReposUseCase): ViewModel() {

    private val _repos = MutableLiveData<State>()
    val repos: LiveData<State> = _repos

    fun repoList(user: String){
        viewModelScope.launch{
            listUserReposUseCase(user)
            .onStart {
                _repos.postValue(State.Loading)
            }
            .catch {
                _repos.postValue(State.Error(it))
            }
            .collect {
                _repos.postValue(State.Success(it))
            }


        }
    }

    sealed class State{
        object Loading : State()
        data class Success(val list: List<Repo>) : State()
        data class Error(val error: Throwable) : State()
    }
}