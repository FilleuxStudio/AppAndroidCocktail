package com.filleuxstudio.appandroidcocktail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filleuxstudio.appandroidcocktail.data.IngredientRepository
import com.filleuxstudio.appandroidcocktail.data.model.IngredientObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class IngredientViewModel: ViewModel() {
    private val ingredientRepository: IngredientRepository by lazy { IngredientRepository() }
    private val _ingredient: Flow<List<IngredientObject>>
        get() = ingredientRepository.selectAll()

    val ingredient = _ingredient
    fun insertAllIngredient() {
        viewModelScope.launch(Dispatchers.IO) {
            ingredientRepository.fetchData("vodka")
        }
    }
    fun  deleteAllIngredient() {
        viewModelScope.launch(Dispatchers.IO) {
            ingredientRepository.deleteAll()
        }
    }
}