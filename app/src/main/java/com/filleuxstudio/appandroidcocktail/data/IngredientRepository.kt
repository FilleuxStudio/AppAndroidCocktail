package com.filleuxstudio.appandroidcocktail.data

import android.util.Log
import com.filleuxstudio.appandroidcocktail.architecture.DatabaseInit
import com.filleuxstudio.appandroidcocktail.architecture.RetrofitBuilderAPITheCocktail
import com.filleuxstudio.appandroidcocktail.data.model.toRoomEntityIngredient
import com.filleuxstudio.appandroidcocktail.data.model.IngredientObject
import com.filleuxstudio.appandroidcocktail.data.model.toUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


// Repository qui gère les opérations liées aux ingrédients des cocktails
class IngredientRepository {
    // Accès à la DAO (Data Access Object) associée aux ingrédients dans la base de données locale
    private val ingredientDAODatabase = DatabaseInit.instance.mApplicationDatabase.IngredientDAO()

//    suspend fun fetchData(Ingredient: String) {
//       val ingredientByName = RetrofitBuilderAPITheCocktail.getCocktailByIngredientName().GetCocktailIngredientName(Ingredient)
//        val ingredientByNameEntities = ingredientByName.toRoomEntityIngredient()
//         ingredientDAODatabase.insertAllIngredients(ingredientByNameEntities)
//        //ingredientDAODatabase.insertIngredient(ingredientByNameEntities)
//    }

    // Fonction pour récupérer des ingrédients par leur nom depuis l'API
    suspend fun fetchData(ingredientName: String, onError: (String) -> Unit) {
        // Appel de l'API pour récupérer les ingrédients par nom
        val response = RetrofitBuilderAPITheCocktail.getCocktailByIngredientName()
            .GetCocktailIngredientName(ingredientName)

        // Vérification si la réponse est réussie et non nulle
        if (response.isSuccessful && response.body() != null) {
            // Récupération de l'objet IngredientDTO depuis la réponse
            val ingredientDTO = response.body()!!

            // Vérification que "ingredients" n'est pas nul et contient des données
            if (ingredientDTO.ingredients.isNullOrEmpty()) {
                onError("Ingrédient non trouvé") // Appeler le callback pour informer l'utilisateur
                return  // Sortie précoce de la fonction pour éviter de traiter des données incorrectes
            }

            // Conversion des ingrédients en entités
            val ingredientEntities = ingredientDTO.toRoomEntityIngredient()

            // Insertion des entités dans la base de données
            ingredientDAODatabase.insertAllIngredients(ingredientEntities)
        } else {
            // Gestion des erreurs
            Log.e(
                "IngredientRepository",
                "Erreur lors de la récupération des ingrédients: ${response.errorBody()}"
            )
            onError("Erreur lors de la récupération des ingrédients")
        }
    }

    // Fonction pour supprimer tous les ingrédients de la base de données locale
    fun deleteAll() {
        ingredientDAODatabase.deleteAllIngredients()
    }

    // Fonction pour récupérer tous les ingrédients stockés dans la base de données sous forme de Flow
    fun selectAll(): Flow<List<IngredientObject>> {
        return ingredientDAODatabase.getAllIngredients().map { list ->
            Log.e("DatabaseItems", "Items in DB: ${list.size}")
            list.toUi()
        }
    }
}