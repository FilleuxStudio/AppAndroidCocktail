package com.filleuxstudio.appandroidcocktail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filleuxstudio.appandroidcocktail.data.IngredientRepository
import com.filleuxstudio.appandroidcocktail.data.model.IngredientObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class IngredientViewModel: ViewModel() {
    private val ingredientRepository: IngredientRepository by lazy { IngredientRepository() }
   /* private val _ingredient: Flow<List<IngredientObject>>
        get() = ingredientRepository.selectAll()*/

    // Utiliser un StateFlow pour exposer les ingrédients observables depuis la base de données
    val ingredient: StateFlow<List<IngredientObject>> = ingredientRepository.selectAll()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    //val ingredient = _ingredient
    fun insertAllIngredient(ingredient: String, onError: (String) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            ingredientRepository.fetchData(ingredient.trim(), onError)
        }
    }

    fun  deleteAllIngredient() {
        viewModelScope.launch(Dispatchers.IO) {
            ingredientRepository.deleteAll()
        }
    }
}