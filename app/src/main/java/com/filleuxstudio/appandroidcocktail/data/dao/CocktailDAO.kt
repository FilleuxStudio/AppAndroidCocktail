package com.filleuxstudio.appandroidcocktail.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.filleuxstudio.appandroidcocktail.data.model.CocktailEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CocktailDAO {

    @Query("SELECT * FROM cocktail ORDER BY name ASC")
    fun getAllCocktails(): Flow<List<CocktailEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCocktail(cocktailEntity: CocktailEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCocktails(cocktails: List<CocktailEntity>)

    @Query("DELETE FROM Cocktail")
    fun deleteAllCocktails()

    @Query("SELECT * FROM Cocktail WHERE id = :cocktailId LIMIT 1")
    fun getCocktailById(cocktailId: String): CocktailEntity?

    @Query("SELECT * FROM Cocktail WHERE category = :category")
    fun getCocktailsByCategory(category: String): Flow<List<CocktailEntity>>

    @Query("SELECT * FROM Cocktail WHERE alcoholic = :alcoholic")
    fun getCocktailsByAlcoholic(alcoholic: String): Flow<List<CocktailEntity>>

    @Update
    fun updateCocktail(cocktailEntity: CocktailEntity)

    @Delete
    fun deleteCocktail(cocktailEntity: CocktailEntity)

    @Query("SELECT * FROM Cocktail WHERE name LIKE '%' || :searchQuery || '%'")
    fun searchCocktails(searchQuery: String): Flow<List<CocktailEntity>>
}
