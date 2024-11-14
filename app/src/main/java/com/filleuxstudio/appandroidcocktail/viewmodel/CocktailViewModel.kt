package com.filleuxstudio.appandroidcocktail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filleuxstudio.appandroidcocktail.data.CocktailsRepository
import com.filleuxstudio.appandroidcocktail.data.model.CocktailObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// ViewModel pour gérer les cocktails et les interactions liées
class CocktailViewModel : ViewModel() {
    // Instance du repository pour accéder aux données des cocktails
    private val repository: CocktailsRepository by lazy { CocktailsRepository() }

    // StateFlow pour contenir la liste des cocktails, initialisé avec une liste vide
    private val _cocktails = MutableStateFlow<List<CocktailObject>>(emptyList())
    // StateFlow pour observer les changements de la liste des cocktails
    val cocktails: StateFlow<List<CocktailObject>> get() = _cocktails

    // Bloc d'initialisation appelé lors de la création du ViewModel
    init {
        loadDefaultCocktails() // Chargement initial des cocktails par défaut
    }

    // Fonction pour charger la liste des cocktails par défaut
    fun loadDefaultCocktails() {
        // Lance une coroutine dans le contexte IO pour exécuter l'opération de manière asynchrone
        viewModelScope.launch(Dispatchers.IO) {
            // Appelle la méthode du repository pour récupérer les cocktails par défaut
            val defaultCocktails = repository.getDefaultCocktails()
            // Met à jour la liste des cocktails en supprimant les doublons selon leur nom
            _cocktails.value = defaultCocktails.distinctBy { it.nameDrink }
        }
    }

    // Fonction pour rechercher des cocktails selon une requête spécifique
    fun searchCocktails(query: String) {
        // Lance une coroutine pour effectuer la recherche de manière asynchrone
        viewModelScope.launch(Dispatchers.IO) {
            // Appelle la méthode du repository pour rechercher les cocktails correspondant à la requête
            val searchResults = repository.searchCocktails(query)
            // Met à jour la liste des cocktails avec les résultats de la recherche, sans doublons
            _cocktails.value = searchResults.distinctBy { it.nameDrink }
        }
    }

    // Fonction pour supprimer tous les cocktails
    fun deleteAllCocktails() {
        // Lance une coroutine pour exécuter l'opération de suppression de manière asynchrone
        viewModelScope.launch(Dispatchers.IO) {
            // Appelle la méthode du repository pour supprimer tous les cocktails
            repository.deleteAll()
            // Met à jour la liste des cocktails pour refléter la suppression
            _cocktails.value = emptyList()
        }
    }
}
