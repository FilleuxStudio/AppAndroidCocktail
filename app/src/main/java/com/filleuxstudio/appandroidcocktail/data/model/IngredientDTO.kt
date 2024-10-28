package com.filleuxstudio.appandroidcocktail.data.model
import com.google.gson.annotations.SerializedName

data class IngredientDTO(
    @SerializedName("ingredients") val ingredients: List<Ingredient> // Changement de List<Ingredient> à Ingredient
)

data class Ingredient(
    @SerializedName("idIngredient") val id: String,
    @SerializedName("strIngredient") val name: String,
    @SerializedName("strDescription") val description: String?,
    @SerializedName("strType") val type: String?,
    @SerializedName("strAlcohol") val isAlcoholic: String?,
    @SerializedName("strABV") val abv: String?
)

fun Ingredient.toRoom(): IngredientEntity {
    return IngredientEntity(
        name = name,
        description = description,
        type = type,
        isAlcoholic = isAlcoholic,
        abv = abv
    )
}

// Modification pour une seule entrée au lieu d'une liste
/*fun IngredientDTO.toRoomEntityIngredient(): IngredientEntity {
    return this.ingredient.toRoom()
}*/

fun IngredientDTO.toRoomEntityIngredient(): List<IngredientEntity> {
    return this.ingredients.map { it.toRoom() }
}