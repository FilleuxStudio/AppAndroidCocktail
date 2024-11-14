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

// ViewModel pour gérer les données des ingrédients
class IngredientViewModel : ViewModel() {
    // Initialisation du repo pour accéder aux données des ingrédients
    private val ingredientRepository: IngredientRepository by lazy { IngredientRepository() }


    // Utilise StateFlow pour exposer les données des ingrédients
    val ingredient: StateFlow<List<IngredientObject>> = ingredientRepository.selectAll()
        .stateIn(
            viewModelScope, // Utilisation du scope du ViewModel pour gérer le cycle de vie du flux
            SharingStarted.Lazily, // Partage du flux : démarre lorsque le premier collecteur s'y abonne
            emptyList() // Valeur initiale avant que les données ne soient chargées
        )

    // Fonction pour insérer ou mettre à jour tous les ingrédients à partir d'une chaîne d'entrée
    fun insertAllIngredient(ingredient: String, onError: (String) -> Unit) {
        // Lance une coroutine dans le contexte IO pour les opérations de base de données
        viewModelScope.launch(Dispatchers.IO) {
            // Appelle la méthode du repository pour récupérer les données à partir de la chaîne entrée
            ingredientRepository.fetchData(ingredient.trim(), onError)
        }
    }

    // Fonction pour supprimer tous les ingrédients de la base de données
    fun deleteAllIngredient() {
        // Lance une coroutine pour effectuer l'opération de suppression
        viewModelScope.launch(Dispatchers.IO) {
            ingredientRepository.deleteAll()
        }
    }
}
