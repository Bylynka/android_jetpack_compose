package com.bylynka.gitrepos_android.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bylynka.gitrepos_android.providers.DataState
import com.bylynka.gitrepos_android.providers.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectsListViewModel @Inject constructor(
    private val projectsProvider: Repository,
) : ViewModel() {

    init {
        refreshProjectsList()
    }

    val dataState = projectsProvider.projectState.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(5000),
        initialValue = DataState.Loading
    )

    fun refreshProjectsList() = viewModelScope.launch {
        projectsProvider.refreshProjects()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ViewModel", "OnCleared")
    }
}