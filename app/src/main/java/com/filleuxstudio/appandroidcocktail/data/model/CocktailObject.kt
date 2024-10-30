package com.filleuxstudio.appandroidcocktail.data.model

data class CocktailObject (
    val nameDrink: String,
    val tags: String,
    val video: String,
    val drinkThumb: String,
    val category: String,
    val  intructions: String,
    val  imageSource: String,
    val  measure: String,
    val glass: String
)

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
            glass = entity.glass ?: ""
        )
    }
}