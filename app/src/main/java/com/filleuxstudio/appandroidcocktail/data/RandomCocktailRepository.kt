package com.filleuxstudio.appandroidcocktail.data

import com.filleuxstudio.appandroidcocktail.architecture.RetrofitBuilderAPITheCocktail
import com.filleuxstudio.appandroidcocktail.data.model.RandomCocktailObject
import com.filleuxstudio.appandroidcocktail.data.model.toUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RandomCocktailRepository {

    // Fonction qui fait une requête à l'API pour obtenir un cocktail aléatoire
    suspend fun fetchRandomCocktail(onError: (String) -> Unit): RandomCocktailObject? {
        return withContext(Dispatchers.IO) {
            try {
                // Appel de l'API pour obtenir un cocktail aléatoire
                val response = RetrofitBuilderAPITheCocktail.getCocktailByRandom().GetCocktailRandom()

                // Vérification si un cocktail est bien retourné dans la liste drinks
                val cocktailDetail = response.drinks?.firstOrNull()
                if (cocktailDetail != null) {
                    cocktailDetail.toUi() // Convertit en RandomCocktailObject pour l'UI
                } else {
                    onError("No cocktail found.")
                    null
                }
            } catch (e: Exception) {
                onError("Failed to load cocktail: ${e.message}")
                null
            }
        }
    }
}
