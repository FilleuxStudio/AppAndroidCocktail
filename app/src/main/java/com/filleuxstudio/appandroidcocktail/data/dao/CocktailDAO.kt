package com.filleuxstudio.appandroidcocktail.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.filleuxstudio.appandroidcocktail.data.model.CocktailEntity
import kotlinx.coroutines.flow.Flow

// Interface DAO (Data Access Object) pour accéder aux données de la table 'Cocktail'
@Dao
interface CocktailDAO {

    // Récupère tous les cocktails de la table, triés par nom
    @Query("SELECT * FROM cocktail ORDER BY name ASC")
    fun getAllCocktails(): Flow<List<CocktailEntity>>

    // Insère un cocktail dans la table. Remplace l'enregistrement existant en cas de conflit.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCocktail(cocktailEntity: CocktailEntity)

    // Insère une liste de cocktails. Remplace les enregistrements existants en cas de conflit.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCocktails(cocktails: List<CocktailEntity>)

    // Supprime tous les cocktails de la table 'Cocktail'
    @Query("DELETE FROM Cocktail")
    fun deleteAllCocktails()

    // Récupère un cocktail par son ID
    @Query("SELECT * FROM Cocktail WHERE id = :cocktailId LIMIT 1")
    fun getCocktailById(cocktailId: String): CocktailEntity?

    // Récupère tous les cocktails appartenant à une catégorie spécifique
    @Query("SELECT * FROM Cocktail WHERE category = :category")
    fun getCocktailsByCategory(category: String): Flow<List<CocktailEntity>>

    // Récupère tous les cocktails filtrés par le type (alcoolisé ou non)
    @Query("SELECT * FROM Cocktail WHERE alcoholic = :alcoholic")
    fun getCocktailsByAlcoholic(alcoholic: String): Flow<List<CocktailEntity>>

    // Met à jour les informations d'un cocktail existant
    @Update
    fun updateCocktail(cocktailEntity: CocktailEntity)

    // Supprime un cocktail spécifique de la table
    @Delete
    fun deleteCocktail(cocktailEntity: CocktailEntity)

    // Recherche les cocktails par leur nom, en utilisant une requête de recherche partielle
    @Query("SELECT * FROM Cocktail WHERE name LIKE '%' || :searchQuery || '%'")
    fun searchCocktails(searchQuery: String): Flow<List<CocktailEntity>>
}
