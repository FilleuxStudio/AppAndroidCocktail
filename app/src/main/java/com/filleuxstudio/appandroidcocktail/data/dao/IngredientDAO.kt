package com.filleuxstudio.appandroidcocktail.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.filleuxstudio.appandroidcocktail.data.model.IngredientEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDAO {
    @Query("SELECT * FROM Ingredient ORDER BY name ASC")
    fun getAllIngredients(): Flow<List<IngredientEntity>>

    @Query("SELECT * FROM Ingredient WHERE id = :id")
    suspend fun getIngredientById(id: Long): IngredientEntity?

    @Query("SELECT * FROM Ingredient WHERE name LIKE '%' || :searchQuery || '%'")
    fun searchIngredients(searchQuery: String): Flow<List<IngredientEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredient(ingredient: IngredientEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllIngredients(ingredients: List<IngredientEntity>)

    @Update
    suspend fun updateIngredient(ingredient: IngredientEntity)

    @Delete
    suspend fun deleteIngredient(ingredient: IngredientEntity)

    @Query("DELETE FROM Ingredient")
    fun deleteAllIngredients()

    @Query("SELECT * FROM Ingredient WHERE type = :type")
    fun getIngredientsByType(type: String): Flow<List<IngredientEntity>>

    @Query("SELECT * FROM Ingredient WHERE is_alcoholic = 'Yes'")
    fun getAlcoholicIngredients(): Flow<List<IngredientEntity>>

    @Query("SELECT * FROM Ingredient WHERE is_alcoholic = 'No'")
    fun getNonAlcoholicIngredients(): Flow<List<IngredientEntity>>

    @Query("SELECT COUNT(*) FROM Ingredient")
    suspend fun getIngredientCount(): Int
}