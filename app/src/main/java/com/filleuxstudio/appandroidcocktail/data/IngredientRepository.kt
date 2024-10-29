package com.filleuxstudio.appandroidcocktail.data

import android.util.Log
import com.filleuxstudio.appandroidcocktail.architecture.DatabaseInit
import com.filleuxstudio.appandroidcocktail.architecture.RetrofitBuilderAPITheCocktail
import com.filleuxstudio.appandroidcocktail.data.model.toRoomEntityIngredient
import com.filleuxstudio.appandroidcocktail.data.model.IngredientObject
import com.filleuxstudio.appandroidcocktail.data.model.toUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class IngredientRepository {
    private val ingredientDAODatabase = DatabaseInit.instance.mApplicationDatabase.IngredientDAO()

//    suspend fun fetchData(Ingredient: String) {
//       val ingredientByName = RetrofitBuilderAPITheCocktail.getCocktailByIngredientName().GetCocktailIngredientName(Ingredient)
//        val ingredientByNameEntities = ingredientByName.toRoomEntityIngredient()
//         ingredientDAODatabase.insertAllIngredients(ingredientByNameEntities)
//        //ingredientDAODatabase.insertIngredient(ingredientByNameEntities)
//    }

    suspend fun fetchData(ingredientName: String) {
        // Appel de l'API pour récupérer les ingrédients par nom
        val response = RetrofitBuilderAPITheCocktail.getCocktailByIngredientName().GetCocktailIngredientName(ingredientName)

        // Vérification si la réponse est réussie et non nulle
        if (response.isSuccessful && response.body() != null) {
            // Récupération de l'objet IngredientDTO depuis la réponse
            val ingredientDTO = response.body()!!
            // Conversion des ingrédients en entités
            val ingredientEntities = ingredientDTO.toRoomEntityIngredient()
            // Insertion des entités dans la base de données
            ingredientDAODatabase.insertAllIngredients(ingredientEntities)
        } else {
            // Gestion des erreurs
            Log.e("IngredientRepository", "Erreur lors de la récupération des ingrédients: ${response.errorBody()}")
        }
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