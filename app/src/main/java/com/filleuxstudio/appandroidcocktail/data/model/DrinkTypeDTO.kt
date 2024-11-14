package com.filleuxstudio.appandroidcocktail.data.model
// Importation de l'annotation Gson pour mapper les champs JSON vers les propriétés Kotlin
import com.google.gson.annotations.SerializedName

// Data class `DrinkTypeDTO` représente la structure JSON des données reçues,
// contenant une liste de `DrinkSummary` pour stocker des informations basiques sur chaque boisson.
data class DrinkTypeDTO(
    @SerializedName("drinks") val drinks: List<DrinkSummary>
)

// `DrinkSummary` contient des informations de base sur une boisson, comme
// le nom, l'URL de la miniature et l'identifiant, utiles pour un aperçu.
data class DrinkSummary(
    @SerializedName("strDrink") val name: String,
    @SerializedName("strDrinkThumb") val thumbnailUrl: String,
    @SerializedName("idDrink") val idDrink: String
)

// Fonction d'extension `toRoom` pour convertir un objet `DrinkSummary` en une entité Room `DrinkTypeEntity`.
// Cela permet de stocker les informations de `DrinkSummary` dans une base de données locale.
fun DrinkSummary.toRoom(): DrinkTypeEntity {
    return DrinkTypeEntity(
        name = name ?: "",
        thumbnailUrl = thumbnailUrl ?: "",
        idDrink = idDrink ?: "",
    )
}

// Fonction d'extension `toRoomEntities` pour convertir une liste de `DrinkTypeDTO` en une liste de `DrinkTypeEntity`.
// Utilisation de `flatMap` pour extraire chaque `DrinkSummary` de la liste de boissons de chaque `DrinkTypeDTO`,
// puis `map` pour les transformer en entités Room.
fun List<DrinkTypeDTO>.toRoomEntities(): List<DrinkTypeEntity> {
    return this.flatMap { it.drinks }.map { it.toRoom() }
}
