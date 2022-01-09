package com.bylynka.gitrepos_android.api

import com.bylynka.gitrepos_android.models.Project
import retrofit2.http.GET

interface IProjectsApi {

    @GET("repos")
    suspend fun geProjects():List<Project>

}