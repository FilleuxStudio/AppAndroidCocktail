package com.filleuxstudio.appandroidcocktail.data.model

// Importation des annotations pour la sérialisation JSON avec Gson
import com.google.gson.annotations.SerializedName

// Data class principale représentant un DTO (Data Transfer Object) pour un cocktail.
// Elle contient une liste de boissons (`Drink`) pouvant être nulle.
data class CocktailDTO(
    @SerializedName("drinks") val drinks: List<Drink>?
)

// Data class représentant les informations d'une boisson (Drink).
// Chaque champ utilise @SerializedName pour lier les noms JSON aux propriétés Kotlin.
data class Drink(
    @SerializedName("idDrink") val id: String,
    @SerializedName("strDrink") val name: String,
    @SerializedName("strDrinkAlternate") val alternativeName: String?,
    @SerializedName("strTags") val tags: String?,
    @SerializedName("strVideo") val videoUrl: String?,
    @SerializedName("strCategory") val category: String,
    @SerializedName("strIBA") val iba: String?,
    @SerializedName("strAlcoholic") val alcoholic: String,
    @SerializedName("strGlass") val glass: String,
    @SerializedName("strInstructions") val instructions: String,
    @SerializedName("strInstructionsES") val instructionsES: String?,
    @SerializedName("strInstructionsDE") val instructionsDE: String?,
    @SerializedName("strInstructionsFR") val instructionsFR: String?,
    @SerializedName("strInstructionsIT") val instructionsIT: String?,
    @SerializedName("strInstructionsZH-HANS") val instructionsZHHANS: String?,
    @SerializedName("strInstructionsZH-HANT") val instructionsZHHANT: String?,
    @SerializedName("strDrinkThumb") val thumbnailUrl: String,
    @SerializedName("strIngredient1") val ingredient1: String?,
    @SerializedName("strIngredient2") val ingredient2: String?,
    @SerializedName("strIngredient3") val ingredient3: String?,
    @SerializedName("strIngredient4") val ingredient4: String?,
    @SerializedName("strIngredient5") val ingredient5: String?,
    @SerializedName("strIngredient6") val ingredient6: String?,
    @SerializedName("strIngredient7") val ingredient7: String?,
    @SerializedName("strIngredient8") val ingredient8: String?,
    @SerializedName("strIngredient9") val ingredient9: String?,
    @SerializedName("strIngredient10") val ingredient10: String?,
    @SerializedName("strIngredient11") val ingredient11: String?,
    @SerializedName("strIngredient12") val ingredient12: String?,
    @SerializedName("strIngredient13") val ingredient13: String?,
    @SerializedName("strIngredient14") val ingredient14: String?,
    @SerializedName("strIngredient15") val ingredient15: String?,
    @SerializedName("strMeasure1") val measure1: String?,
    @SerializedName("strMeasure2") val measure2: String?,
    @SerializedName("strMeasure3") val measure3: String?,
    @SerializedName("strMeasure4") val measure4: String?,
    @SerializedName("strMeasure5") val measure5: String?,
    @SerializedName("strMeasure6") val measure6: String?,
    @SerializedName("strMeasure7") val measure7: String?,
    @SerializedName("strMeasure8") val measure8: String?,
    @SerializedName("strMeasure9") val measure9: String?,
    @SerializedName("strMeasure10") val measure10: String?,
    @SerializedName("strMeasure11") val measure11: String?,
    @SerializedName("strMeasure12") val measure12: String?,
    @SerializedName("strMeasure13") val measure13: String?,
    @SerializedName("strMeasure14") val measure14: String?,
    @SerializedName("strMeasure15") val measure15: String?,
    @SerializedName("strImageSource") val imageSource: String?,
    @SerializedName("strImageAttribution") val imageAttribution: String?,
    @SerializedName("strCreativeCommonsConfirmed") val creativeCommonsConfirmed: String,
    @SerializedName("dateModified") val dateModified: String
)

// Extension fonctionnelle pour convertir un objet Drink en une entité Room `CocktailEntity`.
// Utilise l'opérateur Elvis (`?:`) pour remplacer les valeurs nulles par des chaînes vides.
fun Drink.toRoom(): CocktailEntity {
    return CocktailEntity(
        name = name ?: "",
        alternativeName = alternativeName ?: "",
        tags = tags ?: "",
        videoUrl = videoUrl ?: "",
        category = category ?: "",
        iba = iba ?: "",
        alcoholic = alcoholic ?: "",
        glass = glass ?: "",
        instructions = instructions ?: "",
        instructionsES = instructionsES ?: "",
        instructionsDE = instructionsDE ?: "",
        instructionsFR = instructionsFR ?: "",
        instructionsIT = instructionsIT ?: "",
        instructionsZHHANS = instructionsZHHANS ?: "",
        instructionsZHHANT = instructionsZHHANT ?: "",
        thumbnailUrl = thumbnailUrl ?: "",
        ingredient1 = ingredient1 ?: "",
        ingredient2 = ingredient2 ?: "",
        ingredient3 = ingredient3 ?: "",
        ingredient4 = ingredient4 ?: "",
        ingredient5 = ingredient5 ?: "",
        ingredient6 = ingredient6 ?: "",
        ingredient7 = ingredient7 ?: "",
        ingredient8 = ingredient8 ?: "",
        ingredient9 = ingredient9 ?: "",
        ingredient10 = ingredient10 ?: "",
        ingredient11 = ingredient11 ?: "",
        ingredient12 = ingredient12 ?: "",
        ingredient13 = ingredient13 ?: "",
        ingredient14 = ingredient14 ?: "",
        ingredient15 = ingredient15 ?: "",
        measure1 = measure1 ?: "",
        measure2 = measure2 ?: "",
        measure3 = measure3 ?: "",
        measure4 = measure4 ?: "",
        measure5 = measure5 ?: "",
        measure6 = measure6 ?: "",
        measure7 =  measure7 ?: "",
        measure8 = measure8 ?: "",
        measure9 = measure9 ?: "",
        measure10 = measure10 ?: "",
        measure11 = measure11 ?: "",
        measure12 = measure12 ?: "",
        measure13 = measure13 ?: "",
        measure14 = measure14 ?: "",
        measure15 = measure15 ?: "",
        imageSource = imageSource ?: "",
        imageAttribution = imageAttribution ?: "",
        creativeCommonsConfirmed = creativeCommonsConfirmed ?: "",
        dateModified = dateModified ?: ""
    )
}

// Extension pour convertir une liste de `Drink` en une liste d'entités Room `CocktailEntity`.
fun List<Drink>.toRoomEntitiesCocktail(): List<CocktailEntity> {
    return this.map { it.toRoom() }
}
