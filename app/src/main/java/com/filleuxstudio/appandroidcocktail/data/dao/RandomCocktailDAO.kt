package com.filleuxstudio.appandroidcocktail.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.filleuxstudio.appandroidcocktail.data.model.RandomCocktailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RandomCocktailDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCocktails(cocktails: List<RandomCocktailEntity>)

    @Query("SELECT * FROM RandomCocktail")
    fun getAllCocktails(): Flow<List<RandomCocktailEntity>>

    @Query("DELETE FROM RandomCocktail")
    suspend fun deleteAllCocktails()
}
