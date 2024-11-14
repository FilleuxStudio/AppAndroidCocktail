package com.filleuxstudio.appandroidcocktail.data.model

import com.google.gson.annotations.SerializedName

data class RandomCocktailDTO(
    @SerializedName("drinks") val drinks: List<RandomCocktailDetailDTO>
)

data class RandomCocktailDetailDTO(
    @SerializedName("idDrink") val id: String,
    @SerializedName("strDrink") val name: String,
    @SerializedName("strCategory") val category: String?,
    @SerializedName("strAlcoholic") val alcoholic: String?,
    @SerializedName("strGlass") val glass: String?,
    @SerializedName("strInstructions") val instructions: String?,
    @SerializedName("strDrinkThumb") val imageUrl: String?,
    @SerializedName("strIngredient1") val ingredient1: String?,
    @SerializedName("strIngredient2") val ingredient2: String?,
    @SerializedName("strIngredient3") val ingredient3: String?,
    @SerializedName("strIngredient4") val ingredient4: String?,
    @SerializedName("strIngredient5") val ingredient5: String?,
    @SerializedName("strMeasure1") val measure1: String?,
    @SerializedName("strMeasure2") val measure2: String?,
    @SerializedName("strMeasure3") val measure3: String?,
    @SerializedName("strMeasure4") val measure4: String?,
    @SerializedName("strMeasure5") val measure5: String?
)

// Convertit RandomCocktailDetailDTO en RandomCocktailEntity pour le stockage en base de données
fun RandomCocktailDetailDTO.toRoom(): RandomCocktailEntity {
    val ingredients = listOfNotNull(ingredient1, ingredient2, ingredient3, ingredient4, ingredient5)
    val measures = listOfNotNull(measure1, measure2, measure3, measure4, measure5)
    return RandomCocktailEntity(
        name = name,
        category = category,
        alcoholic = alcoholic,
        glass = glass,
        instructions = instructions,
        imageUrl = imageUrl,
        ingredients = ingredients.joinToString(", "),
        measures = measures.joinToString(", ")
    )
}


fun RandomCocktailEntity.toUi(): RandomCocktailObject {
    return RandomCocktailObject(
        name = name,
        category = category,
        alcoholic = alcoholic,
        glass = glass,
        instructions = instructions,
        imageUrl = imageUrl,
        ingredients = ingredients?.split(", ") ?: emptyList(),
        measures = measures?.split(", ") ?: emptyList()
    )
}
fun RandomCocktailDetailDTO.toUi(): RandomCocktailObject {
    val ingredients = listOfNotNull(ingredient1, ingredient2, ingredient3, ingredient4, ingredient5)
    val measures = listOfNotNull(measure1, measure2, measure3, measure4, measure5)
    return RandomCocktailObject(
        name = name,
        category = category,
        alcoholic = alcoholic,
        glass = glass,
        instructions = instructions,
        imageUrl = imageUrl,
        ingredients = ingredients,
        measures = measures
    )
}
