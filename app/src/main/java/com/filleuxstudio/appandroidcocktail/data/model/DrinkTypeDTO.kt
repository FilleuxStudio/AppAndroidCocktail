package com.filleuxstudio.appandroidcocktail.data.model
import com.google.gson.annotations.SerializedName

data class DrinkTypeDTO(
    @SerializedName("drinks") val drinks: List<DrinkSummary>
)

data class DrinkSummary(
    @SerializedName("strDrink") val name: String,
    @SerializedName("strDrinkThumb") val thumbnailUrl: String,
    @SerializedName("idDrink") val id: String
)
