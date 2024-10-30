package com.filleuxstudio.appandroidcocktail.data

import com.filleuxstudio.appandroidcocktail.architecture.DatabaseInit
import com.filleuxstudio.appandroidcocktail.architecture.RetrofitBuilderAPITheCocktail
import com.filleuxstudio.appandroidcocktail.data.model.CocktailObject
import com.filleuxstudio.appandroidcocktail.data.model.toRoomEntitiesCocktail
import com.filleuxstudio.appandroidcocktail.data.model.toUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RandomRepository {
    private val cocktailDAODatabase = DatabaseInit.instance.mApplicationDatabase.CocktailDAO()

    suspend fun fetchData(cocktailName: String) {
        val cocktailDTO = RetrofitBuilderAPITheCocktail.getCocktailByName().GetCocktailName(cocktailName)

        // Handle nullable drinks field
        val cocktailEntities = cocktailDTO.drinks?.toRoomEntitiesCocktail() ?: emptyList()
        cocktailDAODatabase.insertAllCocktails(cocktailEntities)
    }


    fun deleteAll() {
        cocktailDAODatabase.deleteAllCocktails()
    }

    fun selectAll(): Flow<List<CocktailObject>> {
        return cocktailDAODatabase.getAllCocktails().map { list ->
            list.toUi()
        }
    }
}
