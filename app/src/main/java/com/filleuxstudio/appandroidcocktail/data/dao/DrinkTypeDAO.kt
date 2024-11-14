package com.filleuxstudio.appandroidcocktail.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.filleuxstudio.appandroidcocktail.data.model.DrinkTypeEntity
import kotlinx.coroutines.flow.Flow

// Interface DAO pour la gestion des types de boissons
@Dao
interface DrinkTypeDAO {

    // Récupère tous les types de boissons triés par nom
    @Query("SELECT * FROM DrinkType ORDER BY name ASC")
    fun getAllDrinkTypes(): Flow<List<DrinkTypeEntity>>

    // Récupère un type de boisson par son ID
    @Query("SELECT * FROM DrinkType WHERE id = :id")
    suspend fun getDrinkTypeById(id: Long): DrinkTypeEntity?

    // Recherche les types de boissons dont le nom correspond à la requête de recherche
    @Query("SELECT * FROM DrinkType WHERE name LIKE :searchQuery")
    fun searchDrinkTypes(searchQuery: String): Flow<List<DrinkTypeEntity>>

    // Insère un type de boisson dans la table. Remplace l'enregistrement existant en cas de conflit
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrinkType(drinkType: DrinkTypeEntity): Long

    // Insère une liste de types de boissons. Remplace les enregistrements existants en cas de conflit
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllDrinkTypes(drinkTypes: List<DrinkTypeEntity>)

    // Met à jour les informations d'un type de boisson existant
    @Update
    suspend fun updateDrinkType(drinkType: DrinkTypeEntity)

    // Supprime un type de boisson spécifique de la table
    @Delete
    suspend fun deleteDrinkType(drinkType: DrinkTypeEntity)

    // Supprime tous les enregistrements de la table 'DrinkType'
    @Query("DELETE FROM DrinkType")
    fun deleteAllDrinkTypes()

    // Compte le nombre total de types de boissons présents dans la table
    @Query("SELECT COUNT(*) FROM DrinkType")
    suspend fun getDrinkTypeCount(): Int
}
