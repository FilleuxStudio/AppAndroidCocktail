package com.filleuxstudio.appandroidcocktail.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.filleuxstudio.appandroidcocktail.data.model.IngredientEntity
import kotlinx.coroutines.flow.Flow

// Interface DAO (Data Access Object) pour la gestion des entités 'Ingredient'
@Dao
interface IngredientDAO {

    // Récupère tous les ingrédients de la table 'Ingredient' triés par nom
    @Query("SELECT * FROM Ingredient ORDER BY name ASC")
    fun getAllIngredients(): Flow<List<IngredientEntity>>

    // Récupère un ingrédient spécifique par son ID
    @Query("SELECT * FROM Ingredient WHERE id = :id")
    suspend fun getIngredientById(id: Long): IngredientEntity?

    // Recherche les ingrédients dont le nom contient la chaîne de recherche
    @Query("SELECT * FROM Ingredient WHERE name LIKE '%' || :searchQuery || '%'")
    fun searchIngredients(searchQuery: String): Flow<List<IngredientEntity>>

    // Insère un ingrédient dans la table. Remplace l'enregistrement existant en cas de conflit
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIngredient(ingredient: IngredientEntity): Long

    // Insère une liste d'ingrédients avec une stratégie de remplacement en cas de conflit
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllIngredients(ingredients: List<IngredientEntity>)

    // Met à jour les informations d'un ingrédient existant
    @Update
    suspend fun updateIngredient(ingredient: IngredientEntity)

    // Supprime un ingrédient spécifique de la table
    @Delete
    suspend fun deleteIngredient(ingredient: IngredientEntity)

    // Supprime tous les enregistrements de la table 'Ingredient'
    @Query("DELETE FROM Ingredient")
    fun deleteAllIngredients()

    // Récupère les ingrédients en fonction de leur type (par exemple : alcoolisé ou non)
    @Query("SELECT * FROM Ingredient WHERE type = :type")
    fun getIngredientsByType(type: String): Flow<List<IngredientEntity>>

    // Récupère tous les ingrédients qui sont alcoolisés
    @Query("SELECT * FROM Ingredient WHERE is_alcoholic = 'Yes'")
    fun getAlcoholicIngredients(): Flow<List<IngredientEntity>>

    // Récupère tous les ingrédients qui ne sont pas alcoolisés
    @Query("SELECT * FROM Ingredient WHERE is_alcoholic = 'No'")
    fun getNonAlcoholicIngredients(): Flow<List<IngredientEntity>>

    // Compte le nombre total d'ingrédients présents dans la table
    @Query("SELECT COUNT(*) FROM Ingredient")
    suspend fun getIngredientCount(): Int
}
