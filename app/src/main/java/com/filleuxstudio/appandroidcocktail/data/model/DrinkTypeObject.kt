package com.filleuxstudio.appandroidcocktail.data.model

// Data class `DrinkTypeObject` définit un modèle simplifié pour l'interface utilisateur.
// Il ne contient que les informations essentielles pour l'affichage des boissons.
data class DrinkTypeObject (
    val nameDrink: String,
    val  imageSource: String,
)

// Fonction d'extension `toUi` pour convertir une liste de `DrinkTypeEntity` en une liste de `DrinkTypeObject`.
// Cela permet d'obtenir uniquement les informations nécessaires pour l'interface utilisateur.
fun List<DrinkTypeEntity>.toUi(): List<DrinkTypeObject> {
    return map { entity ->
        DrinkTypeObject(
            nameDrink = entity.name,
            imageSource = entity.thumbnailUrl ?: "",
        )
    }
}