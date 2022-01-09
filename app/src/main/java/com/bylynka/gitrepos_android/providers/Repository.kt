package com.bylynka.gitrepos_android.providers

import com.bylynka.gitrepos_android.api.IProjectsApi
import com.bylynka.gitrepos_android.db.IProjectsDao
import com.bylynka.gitrepos_android.models.Project
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import java.lang.Exception

class Repository(private val api: IProjectsApi, private val dao: IProjectsDao) {

    private val cachedProjects = dao.getProjects()

    private val fetchingState = MutableStateFlow<DataState<List<Project>>>(DataState.Loading)

    val projectState: Flow<DataState<List<Project>>> =
        combine(cachedProjects, fetchingState) { projects, fetching ->
            if (projects.isNotEmpty()) {
                DataState.Success(projects)
            } else {
                fetching
            }
        }

    suspend fun refreshProjects() {
        fetchingState.emit(DataState.Loading)
        //the purpose of delay is to show progress bar a little bit longer, just for demo :)
        delay(3000)
        try {
            val projects = api.geProjects()
            withContext(IO) {
                dao.insert(projects)
            }
            fetchingState.emit(DataState.Success(projects))
        } catch (e: Exception) {
            fetchingState.emit(DataState.Error(e))
        }
    }

    fun getProject(id: Int?) = dao.getProject(id)

}
