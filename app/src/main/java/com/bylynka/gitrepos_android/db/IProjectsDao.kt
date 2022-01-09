package com.bylynka.gitrepos_android.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bylynka.gitrepos_android.models.Project
import kotlinx.coroutines.flow.Flow

@Dao
interface IProjectsDao {

    @Query("Select * from projects")
    fun getProjects(): Flow<List<Project>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(projects: List<Project>)

    @Query("Select * from projects where id=:id limit 1")
    fun getProject(id: Int?): Flow<Project>
}