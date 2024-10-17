package com.filleuxstudio.appandroidcocktail.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "DrinkType")
data class DrinkTypeEntity
    (
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "thumbnail_url")
    val thumbnailUrl: String
    )
