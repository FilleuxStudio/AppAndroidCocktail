package com.filleuxstudio.appandroidcocktail.data

import com.filleuxstudio.appandroidcocktail.architecture.DatabaseInit
import com.filleuxstudio.appandroidcocktail.architecture.RetrofitBuilderAPITheCocktail
import com.filleuxstudio.appandroidcocktail.data.model.DrinkTypeObject
import com.filleuxstudio.appandroidcocktail.data.model.toRoomEntities
import com.filleuxstudio.appandroidcocktail.data.model.toUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Repository qui sert à interagir avec la base de données locale (Room) et l'API pour récupérer des informations sur les types de boissons (cocktails)
class DrinkTypeRepository {
    // Accès à la DAO (Data Access Object) de la base de données pour les types de boissons
    private val drinkTypeDAODatabase = DatabaseInit.instance.mApplicationDatabase.DrinkTypeDAO()

    // Fonction pour récupérer les types de boissons depuis l'API et les insérer dans la base de données locale
    suspend fun fetchData(Type: String) {
        suspend fun fetchData(Type: String) {
            val drinkTypeByName =
                RetrofitBuilderAPITheCocktail.getCocktailByAlcoholic().GetCocktailAlcoholic(Type)
            val drinkTypeByNameEntities = drinkTypeByName.toRoomEntities()
            drinkTypeDAODatabase.insertAllDrinkTypes(drinkTypeByNameEntities)
        }
    }

    // Fonction pour supprimer tous les types de boissons de la base de données locale
    fun deleteAll() {
        drinkTypeDAODatabase.deleteAllDrinkTypes()
    }

    // Fonction pour récupérer tous les types de boissons depuis la base de données locale
    fun selectAll(): Flow<List<DrinkTypeObject>> {
        return drinkTypeDAODatabase.getAllDrinkTypes().map { list ->
            list.toUi()
        }
    }
}