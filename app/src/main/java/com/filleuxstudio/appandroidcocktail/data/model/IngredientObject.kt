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
            description =  entity.description ?: "",
            type = entity.type ?: "",
            isAlcohol = entity.isAlcoholic ?: "",
            abv = entity.abv ?: "",
        )
    }
}