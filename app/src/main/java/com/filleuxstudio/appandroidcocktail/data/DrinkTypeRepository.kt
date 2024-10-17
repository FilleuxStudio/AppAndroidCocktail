package com.filleuxstudio.appandroidcocktail.data

import com.filleuxstudio.appandroidcocktail.architecture.DatabaseInit
import com.filleuxstudio.appandroidcocktail.architecture.RetrofitBuilderAPITheCocktail
import com.filleuxstudio.appandroidcocktail.data.model.DrinkTypeObject
import com.filleuxstudio.appandroidcocktail.data.model.toRoomEntities
import com.filleuxstudio.appandroidcocktail.data.model.toUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DrinkTypeRepository {
    private val drinkTypeDAODatabase = DatabaseInit.instance.mApplicationDatabase.DrinkTypeDAO()
    
    suspend fun fetchData(Type: String) {
        val drinkTypeByName = RetrofitBuilderAPITheCocktail.getCocktailByAlcoholic().GetCocktailAlcoholic(Type)
        val drinkTypeByNameEntities = drinkTypeByName.toRoomEntities()
        drinkTypeDAODatabase.insertAllDrinkTypes(drinkTypeByNameEntities)
    }

    fun deleteAll() {
        drinkTypeDAODatabase.deleteAllDrinkTypes()
    }

    fun selectAll(): Flow<List<DrinkTypeObject>> {
        return drinkTypeDAODatabase.getAllDrinkTypes().map { list ->
            list.toUi()
        }
    }
}