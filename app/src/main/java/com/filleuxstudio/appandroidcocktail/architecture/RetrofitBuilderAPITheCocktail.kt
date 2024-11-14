package com.filleuxstudio.appandroidcocktail.architecture

import com.filleuxstudio.appandroidcocktail.data.model.CocktailDTO
import com.filleuxstudio.appandroidcocktail.data.model.DrinkTypeDTO
import com.filleuxstudio.appandroidcocktail.data.model.IngredientDTO
import com.filleuxstudio.appandroidcocktail.data.model.RandomCocktailDTO
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Objet  pour la configuration de Retrofit pour l'API
object RetrofitBuilderAPITheCocktail {
    // URL de base de l'API
    private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/"
    private const val API_KEY = "1/" // Clé API par défaut

    // Configuration de l'instance Retrofit
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL + API_KEY) // Construction de l'URL complète combiné à la clé API
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())) // Conversion des réponses JSON en objets Kotlin
        .build()

    // Fonction pour rechercher un cocktail par nom
    fun getCocktailByName(): CocktailServiceByName = retrofit.create(CocktailServiceByName::class.java)

    // Fonction pour rechercher un cocktail par sa première lettre
    fun getCocktailByFirstLetter(): CocktailServiceByFirstLetter = retrofit.create(CocktailServiceByFirstLetter::class.java)

    // Fonction pour rechercher un cocktail par ingrédient
    fun getCocktailByIngredientName(): CocktailServiceByIngredientName = retrofit.create(CocktailServiceByIngredientName::class.java)

    // Fonction pour obtenir un cocktail aléatoire
    fun getCocktailByRandom(): CocktailServiceByRandom = retrofit.create(CocktailServiceByRandom::class.java)

    // Fonction pour filtrer les cocktails selon leur type alcoolisé ou non alcoolisé
    fun getCocktailByAlcoholic(): CocktailServiceByAlcoholic = retrofit.create(CocktailServiceByAlcoholic::class.java)
}

// Interface Retrofit pour rechercher un cocktail par son nom
interface CocktailServiceByName {
    @GET("search.php") // Endpoint pour effectuer une recherche par nom
    suspend fun GetCocktailName(@Query("s") NameCocktail: String): CocktailDTO
}

// Interface Retrofit pour rechercher un cocktail par sa première lettre
interface CocktailServiceByFirstLetter {
    @GET("search.php") // Endpoint pour effectuer une recherche par première lettre
    suspend fun GetCocktailFirstLetter(@Query("f") Letter: String): CocktailDTO
}

// Interface Retrofit pour rechercher un cocktail par ingrédient
interface CocktailServiceByIngredientName {
    @GET("search.php") // Endpoint pour effectuer une recherche par ingrédient
    suspend fun GetCocktailIngredientName(@Query("i") IngredientName: String): Response<IngredientDTO>
}

// Interface Retrofit pour obtenir un cocktail aléatoire
interface CocktailServiceByRandom {
    @GET("random.php") // Endpoint pour obtenir un cocktail aléatoire
    suspend fun GetCocktailRandom(): RandomCocktailDTO
}

// Interface Retrofit pour filtrer les cocktails selon leur type alcoolisé ou non alcoolisé
interface CocktailServiceByAlcoholic {
    @GET("filter.php") // Endpoint pour filtrer les cocktails par type alcoolisé
    suspend fun GetCocktailAlcoholic(@Query("a") Type: String): List<DrinkTypeDTO>
}
