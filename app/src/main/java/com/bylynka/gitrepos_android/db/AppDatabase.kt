package com.bylynka.gitrepos_android.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bylynka.gitrepos_android.models.Project

@Database(entities = [Project::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun projectsDao(): IProjectsDao
}