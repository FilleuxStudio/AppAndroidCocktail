package com.filleuxstudio.appandroidcocktail.architecture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.filleuxstudio.appandroidcocktail.repository.FirebaseAuthRepository
import com.filleuxstudio.appandroidcocktail.viewmodel.AuthViewModel

// Factory personnalisée pour créer une instance de AuthViewModel avec un constructeur spécifique
class AuthViewModelFactory(
    private val repository: FirebaseAuthRepository // Dépendance injectée dans le ViewModel
) : ViewModelProvider.Factory {

    // Créer une instance de ViewModel
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Vérification que le type de ViewModel demandé correspond à AuthViewModel
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            // Création et retour d'une instance de AuthViewModel avec le repository fourni
            return AuthViewModel(repository) as T
        }
        //  Exception si le type de ViewModel ne correspond pas à AuthViewModel
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
