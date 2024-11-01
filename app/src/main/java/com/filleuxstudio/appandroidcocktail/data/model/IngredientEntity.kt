package com.filleuxstudio.appandroidcocktail.data.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "Ingredient")
data class IngredientEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0, // L'ID sera généré automatiquement pour chaque enregistrement unique

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

    @ColumnInfo(name = "timestamp")
    val timestamp: Long = System.currentTimeMillis() // Définit la date/heure actuelle par défaut
)