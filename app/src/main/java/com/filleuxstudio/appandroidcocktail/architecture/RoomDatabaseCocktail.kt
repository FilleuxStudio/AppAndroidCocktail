package com.filleuxstudio.appandroidcocktail.architecture

import CocktailEntity
import androidx.room.Database
import androidx.room.RoomDatabase
import com.filleuxstudio.appandroidcocktail.data.dao.CocktailDAO
import com.filleuxstudio.appandroidcocktail.data.model.CocktailDTO

@Database(
    entities = [
        CocktailEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class RoomDatabaseCocktail : RoomDatabase() {
    abstract fun CocktailDAO():CocktailDAO
}