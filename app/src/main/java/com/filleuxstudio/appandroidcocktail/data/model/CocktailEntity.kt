package com.filleuxstudio.appandroidcocktail.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "cocktail")
data class CocktailEntity(
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "alternative_name")
    val alternativeName: String?,

    @ColumnInfo(name = "tags")
    val tags: String?,

    @ColumnInfo(name = "video_url")
    val videoUrl: String?,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "iba")
    val iba: String?,

    @ColumnInfo(name = "alcoholic")
    val alcoholic: String,

    @ColumnInfo(name = "glass")
    val glass: String,

    @ColumnInfo(name = "instructions")
    val instructions: String,

    @ColumnInfo(name = "instructions_es")
    val instructionsES: String?,

    @ColumnInfo(name = "instructions_de")
    val instructionsDE: String?,

    @ColumnInfo(name = "instructions_fr")
    val instructionsFR: String?,

    @ColumnInfo(name = "instructions_it")
    val instructionsIT: String?,

    @ColumnInfo(name = "instructions_zh_hans")
    val instructionsZHHANS: String?,

    @ColumnInfo(name = "instructions_zh_hant")
    val instructionsZHHANT: String?,

    @ColumnInfo(name = "thumbnail_url")
    val thumbnailUrl: String,

    @ColumnInfo(name = "ingredient1")
    val ingredient1: String?,

    @ColumnInfo(name = "ingredient2")
    val ingredient2: String?,

    @ColumnInfo(name = "ingredient3")
    val ingredient3: String?,

    @ColumnInfo(name = "ingredient4")
    val ingredient4: String?,

    @ColumnInfo(name = "ingredient5")
    val ingredient5: String?,

    @ColumnInfo(name = "ingredient6")
    val ingredient6: String?,

    @ColumnInfo(name = "ingredient7")
    val ingredient7: String?,

    @ColumnInfo(name = "ingredient8")
    val ingredient8: String?,

    @ColumnInfo(name = "ingredient9")
    val ingredient9: String?,

    @ColumnInfo(name = "ingredient10")
    val ingredient10: String?,

    @ColumnInfo(name = "ingredient11")
    val ingredient11: String?,

    @ColumnInfo(name = "ingredient12")
    val ingredient12: String?,

    @ColumnInfo(name = "ingredient13")
    val ingredient13: String?,

    @ColumnInfo(name = "ingredient14")
    val ingredient14: String?,

    @ColumnInfo(name = "ingredient15")
    val ingredient15: String?,

    @ColumnInfo(name = "measure1")
    val measure1: String?,

    @ColumnInfo(name = "measure2")
    val measure2: String?,

    @ColumnInfo(name = "measure3")
    val measure3: String?,

    @ColumnInfo(name = "measure4")
    val measure4: String?,

    @ColumnInfo(name = "measure5")
    val measure5: String?,

    @ColumnInfo(name = "measure6")
    val measure6: String?,

    @ColumnInfo(name = "measure7")
    val measure7: String?,

    @ColumnInfo(name = "measure8")
    val measure8: String?,

    @ColumnInfo(name = "measure9")
    val measure9: String?,

    @ColumnInfo(name = "measure10")
    val measure10: String?,

    @ColumnInfo(name = "measure11")
    val measure11: String?,

    @ColumnInfo(name = "measure12")
    val measure12: String?,

    @ColumnInfo(name = "measure13")
    val measure13: String?,

    @ColumnInfo(name = "measure14")
    val measure14: String?,

    @ColumnInfo(name = "measure15")
    val measure15: String?,

    @ColumnInfo(name = "image_source")
    val imageSource: String?,

    @ColumnInfo(name = "image_attribution")
    val imageAttribution: String?,

    @ColumnInfo(name = "creative_commons_confirmed")
    val creativeCommonsConfirmed: String,

    @ColumnInfo(name = "date_modified")
    val dateModified: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
