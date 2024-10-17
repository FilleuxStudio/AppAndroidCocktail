package com.filleuxstudio.appandroidcocktail.data

import com.filleuxstudio.appandroidcocktail.architecture.DatabaseInit
import com.filleuxstudio.appandroidcocktail.architecture.RetrofitBuilderAPITheCocktail
import kotlinx.coroutines.flow.Flow

class CocktailsRepository {
    private val openF1DriverDao = DatabaseInit.instance.mApplicationDatabase.CocktailDTO()


    suspend fun fetchData() {
        //openF1DriverDao.insertDriver(RetrofitBuilderOpenF1.getDriversServiceEndPoint().getRandomF1().toRoom())
        val drivers = RetrofitBuilderAPITheCocktail.getCocktailByName().GetCocktailName()
        val driverEntities = drivers.toRoomEntities()
        openF1DriverDao.insertAllDrivers(driverEntities)
    }


    fun deleteAll() {
        openF1DriverDao.deleteAllDrivers()
    }


    fun selectAll(): Flow<List<OpenF1DriverObject>> {
        return openF1DriverDao.getAllDrivers().map { list ->
            list.toUi()
        }
    }
}