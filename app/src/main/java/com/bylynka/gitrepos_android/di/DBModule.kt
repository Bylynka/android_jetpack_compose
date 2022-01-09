package com.bylynka.gitrepos_android.di

import android.content.Context
import androidx.room.Room
import com.bylynka.gitrepos_android.BuildConfig
import com.bylynka.gitrepos_android.db.AppDatabase
import com.bylynka.gitrepos_android.db.IProjectsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(SingletonComponent::class)
class DBModule {

    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): AppDatabase {
      return Room
            .databaseBuilder(context, AppDatabase::class.java, BuildConfig.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogDAO(db: AppDatabase): IProjectsDao {
        return db.projectsDao()
    }

}