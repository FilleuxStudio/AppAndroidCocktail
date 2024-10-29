package com.filleuxstudio.appandroidcocktail.data.model

data class DrinkTypeObject (
    val nameDrink: String,
    val  imageSource: String,
)

fun List<DrinkTypeEntity>.toUi(): List<DrinkTypeObject> {
    return map { entity ->
        DrinkTypeObject(
            nameDrink = entity.name,
            imageSource = entity.thumbnailUrl ?: "",
        )
    }
}