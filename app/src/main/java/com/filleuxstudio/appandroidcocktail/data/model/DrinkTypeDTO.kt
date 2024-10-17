package com.filleuxstudio.appandroidcocktail.data.model
import com.google.gson.annotations.SerializedName

data class DrinkTypeDTO(
    @SerializedName("drinks") val drinks: List<DrinkSummary>
)

data class DrinkSummary(
    @SerializedName("strDrink") val name: String,
    @SerializedName("strDrinkThumb") val thumbnailUrl: String,
    @SerializedName("idDrink") val idDrink: String
)

fun DrinkSummary.toRoom(): DrinkTypeEntity {
    return DrinkTypeEntity(
        name = name ?: "",
        thumbnailUrl = thumbnailUrl ?: "",
        idDrink = idDrink ?: "",
    )
}

fun List<DrinkTypeDTO>.toRoomEntities(): List<DrinkTypeEntity> {
    return this.flatMap { it.drinks }.map { it.toRoom() }
}
