package com.filleuxstudio.appandroidcocktail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filleuxstudio.appandroidcocktail.data.RandomCocktailRepository
import com.filleuxstudio.appandroidcocktail.data.model.RandomCocktailObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RandomCocktailViewModel : ViewModel() {
    private val randomCocktailRepository: RandomCocktailRepository by lazy { RandomCocktailRepository() }

    // StateFlow pour le cocktail actuel affiché
    private val _currentCocktail = MutableStateFlow<RandomCocktailObject?>(null)
    val currentCocktail: StateFlow<RandomCocktailObject?> = _currentCocktail

    // StateFlow pour le message d'erreur
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    // Récupère un cocktail aléatoire depuis l'API
    fun fetchRandomCocktail() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _errorMessage.value = null // Réinitialiser les erreurs
                val cocktail = randomCocktailRepository.fetchRandomCocktail {  }

                if (cocktail != null) {
                    _currentCocktail.value = cocktail
                } else {
                    _errorMessage.value = "No cocktail found."
                }
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load cocktail: ${e.message}"
            }
        }
    }
}
