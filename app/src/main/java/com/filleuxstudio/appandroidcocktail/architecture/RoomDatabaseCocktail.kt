package com.filleuxstudio.appandroidcocktail.architecture

import androidx.room.Database
import androidx.room.RoomDatabase
import com.filleuxstudio.appandroidcocktail.data.dao.CocktailDAO
import com.filleuxstudio.appandroidcocktail.data.dao.DrinkTypeDAO
import com.filleuxstudio.appandroidcocktail.data.dao.IngredientDAO
import com.filleuxstudio.appandroidcocktail.data.dao.RandomCocktailDAO
import com.filleuxstudio.appandroidcocktail.data.model.CocktailEntity
import com.filleuxstudio.appandroidcocktail.data.model.DrinkTypeEntity
import com.filleuxstudio.appandroidcocktail.data.model.IngredientEntity
import com.filleuxstudio.appandroidcocktail.data.model.RandomCocktailDTO
import com.filleuxstudio.appandroidcocktail.data.model.RandomCocktailEntity

@Database(
    entities = [
        CocktailEntity::class,
        DrinkTypeEntity::class,
        IngredientEntity::class,
        RandomCocktailEntity::class
    ],
    version = 10,
    exportSchema = false
)
abstract class RoomDatabaseCocktail : RoomDatabase() {
    abstract fun CocktailDAO():CocktailDAO
    abstract fun DrinkTypeDAO():DrinkTypeDAO
    abstract fun IngredientDAO():IngredientDAO
    abstract fun RandomCocktailDAO():RandomCocktailDAO
}