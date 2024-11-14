package com.filleuxstudio.appandroidcocktail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filleuxstudio.appandroidcocktail.data.CocktailsRepository
import com.filleuxstudio.appandroidcocktail.data.model.CocktailObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CocktailViewModel : ViewModel() {
    private val repository: CocktailsRepository by lazy { CocktailsRepository() }
    private val _cocktails = MutableStateFlow<List<CocktailObject>>(emptyList())
    val cocktails: StateFlow<List<CocktailObject>> get() = _cocktails

    init {
        loadDefaultCocktails()
    }

    fun loadDefaultCocktails() {
        viewModelScope.launch(Dispatchers.IO) {
            val defaultCocktails = repository.getDefaultCocktails()
            _cocktails.value = defaultCocktails.distinctBy { it.nameDrink }
        }
    }

    fun searchCocktails(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val searchResults = repository.searchCocktails(query)
            _cocktails.value = searchResults.distinctBy { it.nameDrink }
        }
    }

    fun deleteAllCocktails() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
            _cocktails.value = emptyList() // Clear the list after deletion
        }
    }
}
