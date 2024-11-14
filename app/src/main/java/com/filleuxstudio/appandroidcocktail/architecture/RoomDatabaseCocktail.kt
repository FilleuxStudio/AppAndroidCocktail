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
import com.filleuxstudio.appandroidcocktail.data.model.RandomCocktailEntity

// @Database pour spécifier une base de données Room
@Database(
    entities = [
        CocktailEntity::class,
        DrinkTypeEntity::class,
        IngredientEntity::class,
        RandomCocktailEntity::class
    ],
    version = 2, // Version de la base de données
    exportSchema = false // Indique si le schéma de la base de données doit être exporté pour la documentation
)
abstract class RoomDatabaseCocktail : RoomDatabase() {
    // Méthodes abstraites pour obtenir les DAO
    abstract fun CocktailDAO(): CocktailDAO
    abstract fun DrinkTypeDAO(): DrinkTypeDAO
    abstract fun IngredientDAO(): IngredientDAO
    abstract fun RandomCocktailDAO(): RandomCocktailDAO
}
