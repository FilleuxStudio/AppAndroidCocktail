package com.filleuxstudio.appandroidcocktail.data.model

// Classe de données représentant un ingrédient dans l'interface utilisateur.
// Ces données sont utilisées pour afficher les informations de l'ingrédient à l'utilisateur.
data class IngredientObject (
    val nameIngredient: String,
    val description: String,
    val type: String,
    val isAlcohol: String,
    val abv: String
)

// Extension de la classe List<IngredientEntity> pour convertir chaque entité en un objet UI (IngredientObject).
// Ceci permet de séparer la logique de la base de données (les entités) et la logique de présentation (les objets UI).
fun List<IngredientEntity>.toUi(): List<IngredientObject> {
    return map { entity ->
        IngredientObject(
            nameIngredient = entity.name,
            description = entity.description ?: "", // Valeur par défaut si null
            type = entity.type ?: "",               // Valeur par défaut si null
            isAlcohol = entity.isAlcoholic ?: "",   // Valeur par défaut si null
            abv = entity.abv ?: ""                   // Valeur par défaut si null
        )
    }
}