package com.filleuxstudio.appandroidcocktail.data

import com.filleuxstudio.appandroidcocktail.architecture.DatabaseInit
import com.filleuxstudio.appandroidcocktail.architecture.RetrofitBuilderAPITheCocktail
import com.filleuxstudio.appandroidcocktail.data.model.CocktailObject
import com.filleuxstudio.appandroidcocktail.data.model.toRoomEntitiesCocktail
import com.filleuxstudio.appandroidcocktail.data.model.toUi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

// Repository qui sert à interagir avec la base de données locale (Room) et l'API pour récupérer des informations sur les cocktails
class CocktailsRepository {
    // Accès à la DAO (Data Access Object) de la base de données pour les cocktails
    private val cocktailDAODatabase = DatabaseInit.instance.mApplicationDatabase.CocktailDAO()

    // Fonction qui récupère les cocktails par défaut depuis la base de données locale.
    // Si aucune donnée n'est trouvée dans la base locale, elle pourrait aussi interroger l'API.
    suspend fun getDefaultCocktails(): List<CocktailObject> {
        // Fetch a default set of cocktails from the API or local database
        return cocktailDAODatabase.getAllCocktails().map { list ->
            list.toUi()
        }.first() // Take the first result or handle appropriately based on your use case
    }

    // Fonction pour rechercher des cocktails à partir d'un terme de recherche.
    // Elle appelle l'API pour mettre à jour les données, puis récupère les résultats depuis la base de données locale.
    suspend fun searchCocktails(query: String): List<CocktailObject> {

        fetchData(query)

        val searchResults = cocktailDAODatabase.searchCocktails(query).map { list ->
            list.toUi()
        }.first() // Handle null and fetch results from local DB
        return searchResults
    }

    // Fonction qui récupère les données depuis l'API et les insère dans la base de données locale
    suspend fun fetchData(cocktailName: String) {
        val cocktailDTO =
            RetrofitBuilderAPITheCocktail.getCocktailByName().GetCocktailName(cocktailName)

        // Handle nullable drinks field
        val cocktailEntities = cocktailDTO.drinks?.toRoomEntitiesCocktail() ?: emptyList()
        cocktailDAODatabase.insertAllCocktails(cocktailEntities)
    }

    // Fonction qui supprime tous les cocktails de la base de données locale
    fun deleteAll() {
        cocktailDAODatabase.deleteAllCocktails()
    }
}