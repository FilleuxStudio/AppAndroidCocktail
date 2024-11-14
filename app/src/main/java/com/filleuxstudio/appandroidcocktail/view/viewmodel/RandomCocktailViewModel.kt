package com.filleuxstudio.appandroidcocktail.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filleuxstudio.appandroidcocktail.data.RandomCocktailRepository
import com.filleuxstudio.appandroidcocktail.data.model.RandomCocktailObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// ViewModel pour gérer les données des cocktails aléatoires
class RandomCocktailViewModel : ViewModel() {
    // Instance du repository pour accéder aux cocktails aléatoires
    private val randomCocktailRepository: RandomCocktailRepository by lazy { RandomCocktailRepository() }

    // StateFlow pour contenir le cocktail aléatoire actuel
    private val _currentCocktail = MutableStateFlow<RandomCocktailObject?>(null)
    // StateFlow pour observer le cocktail actuel
    val currentCocktail: StateFlow<RandomCocktailObject?> = _currentCocktail

    // StateFlow pour contenir les messages d'erreur
    private val _errorMessage = MutableStateFlow<String?>(null)
    // StateFlow pour observer les erreurs
    val errorMessage: StateFlow<String?> = _errorMessage

    // Fonction pour récupérer un cocktail aléatoire depuis l'API
    fun fetchRandomCocktail() {
        // Lance une coroutine pour exécuter la tâche de manière asynchrone
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Réinitialise le message d'erreur
                _errorMessage.value = null
                // Appelle la méthode du repository pour récupérer un cocktail aléatoire
                val cocktail = randomCocktailRepository.fetchRandomCocktail {}

                // Vérifie si un cocktail a été récupéré
                if (cocktail != null) {
                    _currentCocktail.value = cocktail // Met à jour le StateFlow avec le cocktail récupéré
                } else {
                    _errorMessage.value = "No cocktail found." // Met à jour le StateFlow avec un message d'erreur
                }
            } catch (e: Exception) {
                // Gère les exceptions et met à jour le StateFlow avec un message d'erreur
                _errorMessage.value = "Failed to load cocktail: ${e.message}"
            }
        }
    }
}
