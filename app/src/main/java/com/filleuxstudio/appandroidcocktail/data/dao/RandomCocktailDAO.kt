package com.filleuxstudio.appandroidcocktail.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.filleuxstudio.appandroidcocktail.data.model.RandomCocktailEntity
import kotlinx.coroutines.flow.Flow

// Interface DAO (Data Access Object) pour la gestion des cocktails aléatoires dans Room
@Dao
interface RandomCocktailDAO {

    // Insère une liste de cocktails aléatoires dans la table. Remplace les enregistrements existants en cas de conflit
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCocktails(cocktails: List<RandomCocktailEntity>)

    // Récupère tous les cocktails aléatoires de la table
    @Query("SELECT * FROM RandomCocktail")
    fun getAllCocktails(): Flow<List<RandomCocktailEntity>>

    // Supprime tous les cocktails de la table 'RandomCocktail'
    @Query("DELETE FROM RandomCocktail")
    suspend fun deleteAllCocktails()
}
