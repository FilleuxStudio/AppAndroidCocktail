package com.filleuxstudio.appandroidcocktail.data.model

import com.google.gson.annotations.SerializedName
data class IngredientDTO(
    @SerializedName("ingredients") val ingredients: List<Ingredient>
)

data class Ingredient(
    @SerializedName("idIngredient") val id: String,
    @SerializedName("strIngredient") val name: String,
    @SerializedName("strDescription") val description: String?,
    @SerializedName("strType") val type: String?,
    @SerializedName("strAlcohol") val isAlcoholic: String?,
    @SerializedName("strABV") val abv: String?
)