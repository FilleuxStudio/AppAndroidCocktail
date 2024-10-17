package com.filleuxstudio.appandroidcocktail.data

import com.filleuxstudio.appandroidcocktail.architecture.DatabaseInit
import com.filleuxstudio.appandroidcocktail.architecture.RetrofitBuilderAPITheCocktail
import com.filleuxstudio.appandroidcocktail.data.model.toRoomEntities
import kotlinx.coroutines.flow.Flow

class CocktailsRepository {
  /*  private val cocktailDAODatabase = DatabaseInit.instance.mApplicationDatabase.CocktailDAO()


    suspend fun fetchData() {
        //openF1DriverDao.insertDriver(RetrofitBuilderOpenF1.getDriversServiceEndPoint().getRandomF1().toRoom())
       val cocktailByName = RetrofitBuilderAPITheCocktail.getCocktailByName().GetCocktailName("e")
        val cocktailByNameEntities = cocktailByName.toRoomEntities()
        //cocktailDAODatabase.insertCocktail(cocktailByNameEntities)
    }


    fun deleteAll() {
        cocktailDAODatabase.deleteAllCocktails()
    }


   /* fun selectAll(): Flow<List<OpenF1DriverObject>> {
        return openF1DriverDao.getAllDrivers().map { list ->
            list.toUi()
        }
    }*/*/
}