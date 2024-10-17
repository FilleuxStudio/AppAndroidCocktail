package com.filleuxstudio.appandroidcocktail.data

import com.filleuxstudio.appandroidcocktail.architecture.DatabaseInit
import com.filleuxstudio.appandroidcocktail.architecture.RetrofitBuilderAPITheCocktail
import com.filleuxstudio.appandroidcocktail.data.model.DrinkTypeObject
import com.filleuxstudio.appandroidcocktail.data.model.IngredientObject
import com.filleuxstudio.appandroidcocktail.data.model.toRoomEntities
import com.filleuxstudio.appandroidcocktail.data.model.toRoomEntitiesIngredient
import com.filleuxstudio.appandroidcocktail.data.model.toUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class IngredientRepository {
    private val ingredientDAODatabase = DatabaseInit.instance.mApplicationDatabase.IngredientDAO()

    suspend fun fetchData(Ingredient: String) {
        val ingredientByName = RetrofitBuilderAPITheCocktail.getCocktailByIngredientName().GetCocktailIngredientName(Ingredient)
        val ingredientByNameEntities = ingredientByName.toRoomEntitiesIngredient()
        ingredientDAODatabase.insertAllIngredients(ingredientByNameEntities)
    }

    fun deleteAll() {
        ingredientDAODatabase.deleteAllIngredients()
    }

    fun selectAll(): Flow<List<IngredientObject>> {
        return ingredientDAODatabase.getAllIngredients().map { list ->
            list.toUi()
        }
    }
}