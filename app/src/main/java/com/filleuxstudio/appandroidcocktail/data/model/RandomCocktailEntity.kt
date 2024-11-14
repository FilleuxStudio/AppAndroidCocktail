package com.filleuxstudio.appandroidcocktail.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

// Entité représentant un cocktail aléatoire dans la base de données
@Entity(tableName = "RandomCocktail")
data class RandomCocktailEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0, // L'ID sera généré automatiquement pour chaque enregistrement unique
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "category")
    val category: String?,
    @ColumnInfo(name = "alcoholic")
    val alcoholic: String?,
    @ColumnInfo(name = "glass")
    val glass: String?,
    @ColumnInfo(name = "instructions")
    val instructions: String?,
    @ColumnInfo(name = "image_url")
    val imageUrl: String?,
    @ColumnInfo(name = "ingredients")
    val ingredients: String?, // Stocké en tant que chaîne de texte séparée par des virgules
    @ColumnInfo(name = "measures")
    val measures: String?, // Stocké en tant que chaîne de texte séparée par des virgules
    @ColumnInfo(name = "timestamp")
    val timestamp: Long = System.currentTimeMillis() // Définit la date/heure actuelle par défaut
)
