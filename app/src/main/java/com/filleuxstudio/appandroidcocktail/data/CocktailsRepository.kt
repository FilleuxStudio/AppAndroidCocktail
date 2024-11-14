package com.filleuxstudio.appandroidcocktail.data

import com.filleuxstudio.appandroidcocktail.architecture.DatabaseInit
import com.filleuxstudio.appandroidcocktail.architecture.RetrofitBuilderAPITheCocktail
import com.filleuxstudio.appandroidcocktail.data.model.CocktailObject
import com.filleuxstudio.appandroidcocktail.data.model.toRoomEntitiesCocktail
import com.filleuxstudio.appandroidcocktail.data.model.toUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class CocktailsRepository {
    private val cocktailDAODatabase = DatabaseInit.instance.mApplicationDatabase.CocktailDAO()

    suspend fun getDefaultCocktails(): List<CocktailObject> {
        // Fetch a default set of cocktails from the API or local database
        return cocktailDAODatabase.getAllCocktails().map { list ->
            list.toUi()
        }.first() // Take the first result or handle appropriately based on your use case
    }

    suspend fun searchCocktails(query: String): List<CocktailObject> {

        fetchData(query)

        val searchResults = cocktailDAODatabase.searchCocktails(query).map { list ->
            list.toUi()
        }.first() // Handle null and fetch results from local DB
        return searchResults
    }

    suspend fun fetchData(cocktailName: String) {
        val cocktailDTO = RetrofitBuilderAPITheCocktail.getCocktailByName().GetCocktailName(cocktailName)

        // Handle nullable drinks field
        val cocktailEntities = cocktailDTO.drinks?.toRoomEntitiesCocktail() ?: emptyList()
        cocktailDAODatabase.insertAllCocktails(cocktailEntities)
    }

    fun deleteAll() {
        cocktailDAODatabase.deleteAllCocktails()
    }
}
