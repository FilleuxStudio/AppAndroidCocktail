package com.filleuxstudio.appandroidcocktail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filleuxstudio.appandroidcocktail.data.FirebaseAuthRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: FirebaseAuthRepository) : ViewModel() {
    private val _user = MutableStateFlow<FirebaseUser?>(repository.getCurrentUser())
    val user: StateFlow<FirebaseUser?> get() = _user

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            if (email.isBlank() || password.isBlank()) {
                _errorMessage.value = "Email et mot de passe ne peuvent pas être vides"
                return@launch
            }

            if (password.length < 6) {
                _errorMessage.value = "Le mot de passe doit contenir au moins 6 caractères"
                return@launch
            }

            val result = repository.signUp(email, password)
            result.fold(
                onSuccess = { user ->
                    _user.value = user
                    _errorMessage.value = null
                },
                onFailure = { exception ->
                    _errorMessage.value = exception.localizedMessage ?: "Erreur d'inscription inconnue"
                }
            )
        }
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            if (email.isBlank() || password.isBlank()) {
                _errorMessage.value = "Email et mot de passe ne peuvent pas être vides"
                return@launch
            }

            val result = repository.signIn(email, password)
            result.fold(
                onSuccess = { user ->
                    _user.value = user
                    _errorMessage.value = null
                },
                onFailure = { exception ->
                    _errorMessage.value = exception.localizedMessage ?: "Erreur de connexion inconnue"
                }
            )
        }
    }

    fun signOut() {
        repository.signOut()
        _user.value = null
    }
}
