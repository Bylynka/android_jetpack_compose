package com.bylynka.gitrepos_android.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projects")
class Project {

    @PrimaryKey
    var id: Int? = null

    var name: String? = null

    var description: String? = null

    var html_url: String? = null
}