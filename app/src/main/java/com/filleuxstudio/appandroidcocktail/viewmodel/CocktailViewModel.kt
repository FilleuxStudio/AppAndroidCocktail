package com.filleuxstudio.appandroidcocktail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filleuxstudio.appandroidcocktail.data.CocktailsRepository
import com.filleuxstudio.appandroidcocktail.data.model.CocktailObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CocktailViewModel: ViewModel() {
    private val cocktailsRepository: CocktailsRepository by lazy { CocktailsRepository() }
    private val _cocktail: Flow<List<CocktailObject>>
        get() = cocktailsRepository.selectAll()

    val cocktail = _cocktail
    fun insertAllCocktails() {
        viewModelScope.launch(Dispatchers.IO) {
            cocktailsRepository.fetchData("Teee")
        }
    }
    fun  deleteAllCocktails() {
        viewModelScope.launch(Dispatchers.IO) {
            cocktailsRepository.deleteAll()
        }
    }
}