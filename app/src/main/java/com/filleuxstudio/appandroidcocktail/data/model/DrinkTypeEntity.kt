package com.filleuxstudio.appandroidcocktail.data.model

// Importation des annotations Room pour définir l'entité et ses colonnes
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

// `DrinkTypeEntity` représente une entité Room, utilisée pour stocker les informations
// basiques d'une boisson dans une table de base de données locale.
// La table est nommée "DrinkType" et chaque instance de `DrinkTypeEntity` représente une entrée dans cette table.
@Entity(tableName = "DrinkType")
data class DrinkTypeEntity
    (
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "thumbnail_url")
    val thumbnailUrl: String,

    @ColumnInfo(name = "idDrink")
    val idDrink: String
) {
    // Clé primaire générée automatiquement pour chaque entrée dans la table
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}