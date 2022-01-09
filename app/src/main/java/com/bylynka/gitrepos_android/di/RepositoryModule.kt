package com.bylynka.gitrepos_android.di

import com.bylynka.gitrepos_android.api.IProjectsApi
import com.bylynka.gitrepos_android.db.AppDatabase
import com.bylynka.gitrepos_android.providers.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(api: IProjectsApi, db: AppDatabase) = Repository(api, db.projectsDao())

}