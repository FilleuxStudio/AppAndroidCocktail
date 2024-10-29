package com.filleuxstudio.appandroidcocktail.architecture

import androidx.room.Database
import androidx.room.RoomDatabase
import com.filleuxstudio.appandroidcocktail.data.dao.CocktailDAO
import com.filleuxstudio.appandroidcocktail.data.dao.DrinkTypeDAO
import com.filleuxstudio.appandroidcocktail.data.dao.IngredientDAO
import com.filleuxstudio.appandroidcocktail.data.model.CocktailEntity
import com.filleuxstudio.appandroidcocktail.data.model.DrinkTypeEntity
import com.filleuxstudio.appandroidcocktail.data.model.IngredientEntity

@Database(
    entities = [
        CocktailEntity::class,
        DrinkTypeEntity::class,
        IngredientEntity::class,
    ],
    version = 5,
    exportSchema = false
)
abstract class RoomDatabaseCocktail : RoomDatabase() {
    abstract fun CocktailDAO():CocktailDAO
    abstract fun DrinkTypeDAO():DrinkTypeDAO
    abstract fun IngredientDAO():IngredientDAO
}