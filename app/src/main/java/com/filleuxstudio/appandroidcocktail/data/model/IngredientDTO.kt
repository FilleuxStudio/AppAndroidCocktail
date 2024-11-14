package com.filleuxstudio.appandroidcocktail.data.model
// Importation de la bibliothèque Gson pour la désérialisation JSON
import com.google.gson.annotations.SerializedName

// `IngredientDTO` représente la structure de réponse JSON pour une liste d'ingrédients
// provenant de l'API. Elle contient une liste d'objets `Ingredient`.
data class IngredientDTO(
    @SerializedName("ingredients") val ingredients: List<Ingredient> // Changement de List<Ingredient> à Ingredient
)

// `Ingredient` représente un ingrédient unique avec ses propriétés spécifiques.
// Cette data class est utilisée pour stocker les informations sur chaque ingrédient,
// telles que récupérées depuis l'API JSON.
data class Ingredient(
    @SerializedName("idIngredient") val id: String,
    @SerializedName("strIngredient") val name: String,
    @SerializedName("strDescription") val description: String?,
    @SerializedName("strType") val type: String?,
    @SerializedName("strAlcohol") val isAlcoholic: String?,
    @SerializedName("strABV") val abv: String?
)

// Fonction d'extension `toRoom` pour convertir un `Ingredient` en un `IngredientEntity`
// qui représente l'ingrédient sous une forme compatible avec la base de données locale (Room).
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

// Fonction de transformation pour convertir `IngredientDTO` en une liste d'objets `IngredientEntity`.
// Parcourt chaque ingrédient dans la liste `ingredients` et utilise `toRoom` pour les transformer
// en `IngredientEntity`, adaptés pour la base de données.
fun IngredientDTO.toRoomEntityIngredient(): List<IngredientEntity> {
    return this.ingredients.map { it.toRoom() }
}