package com.filleuxstudio.appandroidcocktail.data.model

data class IngredientObject (
    val nameIngredient: String,
    val description: String,
    val type: String,
    val isAlcohol: String,
    val abv: String
)

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