package com.bylynka.gitrepos_android.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bylynka.gitrepos_android.providers.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class ProjectViewModel @Inject constructor(
    projectsProvider: Repository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val project = projectsProvider.getProject(savedStateHandle.get("id")).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

}