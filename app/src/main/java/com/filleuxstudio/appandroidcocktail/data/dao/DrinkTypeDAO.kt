package com.filleuxstudio.appandroidcocktail.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.filleuxstudio.appandroidcocktail.data.model.DrinkTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DrinkTypeDAO {
    @Query("SELECT * FROM DrinkType ORDER BY name ASC")
    fun getAllDrinkTypes(): Flow<List<DrinkTypeEntity>>

    @Query("SELECT * FROM DrinkType WHERE id = :id")
    suspend fun getDrinkTypeById(id: Long): DrinkTypeEntity?

    @Query("SELECT * FROM DrinkType WHERE name LIKE :searchQuery")
    fun searchDrinkTypes(searchQuery: String): Flow<List<DrinkTypeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrinkType(drinkType: DrinkTypeEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllDrinkTypes(drinkTypes: List<DrinkTypeEntity>)

    @Update
    suspend fun updateDrinkType(drinkType: DrinkTypeEntity)

    @Delete
    suspend fun deleteDrinkType(drinkType: DrinkTypeEntity)

    @Query("DELETE FROM DrinkType")
    fun deleteAllDrinkTypes()

    @Query("SELECT COUNT(*) FROM DrinkType")
    suspend fun getDrinkTypeCount(): Int
}