package com.filleuxstudio.appandroidcocktail.data.model
// Import des annotations de Room pour définir la structure de la table
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

// `IngredientEntity` représente un ingrédient pour la base de données locale sous forme d'entité Room.
// La table est nommée "Ingredient" et contient les colonnes pour stocker les attributs d'un ingrédient.
@Entity(tableName = "Ingredient")
data class IngredientEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0, // Valeur par défaut de 0 ; sera remplacée par un ID unique auto-généré
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "type")
    val type: String?,
    @ColumnInfo(name = "is_alcoholic")
    val isAlcoholic: String?,
    @ColumnInfo(name = "abv")
    val abv: String?,

    // `timestamp` enregistre l'heure à laquelle l'ingrédient est ajouté ou mis à jour dans la base de données.
    // Initialisé par défaut avec l'heure actuelle en millisecondes.
    @ColumnInfo(name = "timestamp")
    val timestamp: Long = System.currentTimeMillis()
)