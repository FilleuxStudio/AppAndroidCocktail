package com.filleuxstudio.appandroidcocktail.data.model

// La classe RandomCocktailObject est un modèle utilisé pour afficher un cocktail dans l'interface utilisateur.
data class RandomCocktailObject(
    val name: String,
    val category: String?,
    val alcoholic: String?,
    val glass: String?,
    val instructions: String?,
    val imageUrl: String?,
    val ingredients: List<String>,
    val measures: List<String>
)

// Fonction d'extension pour convertir un objet RandomCocktailDetailDTO en RandomCocktailEntity (utilisé pour stocker dans la base de données)
fun RandomCocktailDetailDTO.toEntity(): RandomCocktailEntity {
    return RandomCocktailEntity(
        name = name,
        category = category,
        alcoholic = alcoholic,
        glass = glass,
        instructions = instructions,
        imageUrl = imageUrl,
        ingredients = listOfNotNull(ingredient1, ingredient2, ingredient3, ingredient4, ingredient5).joinToString(", "),
        measures = listOfNotNull(measure1, measure2, measure3, measure4, measure5).joinToString(", ")
    )
}