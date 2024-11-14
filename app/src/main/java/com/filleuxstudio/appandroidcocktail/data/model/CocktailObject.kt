package com.filleuxstudio.appandroidcocktail.data.model

// Data class `CocktailObject` représente un modèle de données simplifié,
// adapté à l'affichage en interface utilisateur.
// Contient seulement les champs nécessaires pour l'UI, sans les détails supplémentaires de la base de données.
data class CocktailObject (
    val nameDrink: String,
    val tags: String,
    val video: String,
    val drinkThumb: String,
    val category: String,
    val  intructions: String,
    val  imageSource: String,
    val  measure: String,
    val glass: String,
    val dateInserted: String
)

// Extension fonctionnelle `toUi` pour convertir une liste de `CocktailEntity` en une liste de `CocktailObject`,
// ce qui simplifie la gestion des données pour l'affichage.
fun List<CocktailEntity>.toUi(): List<CocktailObject> {
    return map { entity ->
        CocktailObject(
            nameDrink = entity.name,
            tags = entity.tags ?: "",
            video = entity.videoUrl ?: "",
            drinkThumb = entity.thumbnailUrl,
            category = entity.category,
            intructions = entity.instructions,
            imageSource = entity.imageSource ?: "",
            measure = entity.measure1 ?: "",
            glass = entity.glass ?: "",
            dateInserted = entity.dateModified ?: ""
        )
    }
}