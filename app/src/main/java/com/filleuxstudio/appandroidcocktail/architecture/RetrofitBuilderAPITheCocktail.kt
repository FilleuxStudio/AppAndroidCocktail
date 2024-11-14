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

object RetrofitBuilderAPITheCocktail {
    private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/"
    private const val API_KEY = "1/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL + API_KEY)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    fun getCocktailByName(): CocktailServiceByName = retrofit.create(CocktailServiceByName::class.java)
    fun getCocktailByFirstLetter(): CocktailServiceByFirstLetter = retrofit.create(CocktailServiceByFirstLetter::class.java)
    fun getCocktailByIngredientName(): CocktailServiceByIngredientName = retrofit.create(CocktailServiceByIngredientName::class.java)
    fun getCocktailByRandom(): CocktailServiceByRandom = retrofit.create(CocktailServiceByRandom::class.java)
    fun getCocktailByAlcoholic(): CocktailServiceByAlcoholic = retrofit.create(CocktailServiceByAlcoholic::class.java)
}

interface CocktailServiceByName {
    @GET("search.php")
    suspend fun GetCocktailName(@Query("s") NameCocktail: String): CocktailDTO
}

interface CocktailServiceByFirstLetter {
    @GET("search.php")
    suspend fun GetCocktailFirstLetter(@Query("f") Letter: String): CocktailDTO
}

interface CocktailServiceByIngredientName {
    @GET("search.php")
    suspend fun GetCocktailIngredientName(@Query("i") IngredientName: String): Response<IngredientDTO>
}

interface CocktailServiceByRandom {
    @GET("random.php")
    suspend fun GetCocktailRandom(): RandomCocktailDTO
}

interface CocktailServiceByAlcoholic {
    @GET("filter.php")
    suspend fun GetCocktailAlcoholic(@Query("a") Type: String): List<DrinkTypeDTO>
}
