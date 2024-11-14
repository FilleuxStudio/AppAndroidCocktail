package com.filleuxstudio.appandroidcocktail.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filleuxstudio.appandroidcocktail.data.FirebaseAuthRepository
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

// ViewModel qui gère les opérations d'authentification de l'utilisateur,
// utilisant Firebase pour les fonctionnalités de connexion, inscription, et déconnexion.
class AuthViewModel(private val repository: FirebaseAuthRepository) : ViewModel() {

    // StateFlow pour stocker l'utilisateur connecté actuel.
    private val _user = MutableStateFlow<FirebaseUser?>(repository.getCurrentUser())
    // Exposition publique en lecture seule du flux d'état utilisateur.
    val user: StateFlow<FirebaseUser?> get() = _user

    // StateFlow pour gérer et exposer les messages d'erreur d'authentification.
    private val _errorMessage = MutableStateFlow<String?>(null)
    // Exposition publique en lecture seule du flux d'état des messages d'erreur.
    val errorMessage: StateFlow<String?> get() = _errorMessage

    // Fonction pour inscrire un utilisateur en utilisant un email et un mot de passe.
    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            // Vérification des champs vides.
            if (email.isBlank() || password.isBlank()) {
                _errorMessage.value = "Email et mot de passe ne peuvent pas être vides"
                return@launch
            }

            // Vérification de la longueur minimale du mot de passe.
            if (password.length < 6) {
                _errorMessage.value = "Le mot de passe doit contenir au moins 6 caractères"
                return@launch
            }

            // Tentative d'inscription via le repository.
            val result = repository.signUp(email, password)
            result.fold(
                onSuccess = { user ->
                    // Mise à jour de l'utilisateur et réinitialisation du message d'erreur en cas de succès.
                    _user.value = user
                    _errorMessage.value = null
                },
                onFailure = { exception ->
                    // Mise à jour du message d'erreur en cas d'échec de l'inscription.
                    _errorMessage.value = exception.localizedMessage ?: "Erreur d'inscription inconnue"
                }
            )
        }
    }

    // Fonction pour connecter un utilisateur en utilisant un email et un mot de passe.
    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            // Vérification des champs vides.
            if (email.isBlank() || password.isBlank()) {
                _errorMessage.value = "Email et mot de passe ne peuvent pas être vides"
                return@launch
            }

            // Tentative de connexion via le repository.
            val result = repository.signIn(email, password)
            result.fold(
                onSuccess = { user ->
                    // Mise à jour de l'utilisateur et réinitialisation du message d'erreur en cas de succès.
                    _user.value = user
                    _errorMessage.value = null
                },
                onFailure = { exception ->
                    // Mise à jour du message d'erreur en cas d'échec de la connexion.
                    _errorMessage.value = exception.localizedMessage ?: "Erreur de connexion inconnue"
                }
            )
        }
    }

    // Fonction pour connecter un utilisateur avec un compte Google.
    fun signInWithGoogle(account: GoogleSignInAccount) {
        viewModelScope.launch {
            // Création des informations d'identification à partir du compte Google.
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            try {
                // Tentative de connexion via Firebase Authentication.
                val authResult = FirebaseAuth.getInstance().signInWithCredential(credential).await()
                // Mise à jour de l'utilisateur et réinitialisation du message d'erreur en cas de succès.
                _user.value = authResult.user
                _errorMessage.value = null
                // Affichage d'un message de réussite dans la console (à des fins de débogage).
                println("Utilisateur connecté : ${authResult.user?.uid}")
            } catch (e: Exception) {
                // Mise à jour du message d'erreur en cas d'échec de la connexion avec Google.
                _errorMessage.value = "Erreur lors de la connexion avec Google : ${e.localizedMessage}"
                // Affichage de l'erreur dans la console (à des fins de débogage).
                println("Erreur lors de la connexion avec Google : ${e.localizedMessage}")
            }
        }
    }

    // Fonction pour déconnecter l'utilisateur actuel.
    fun signOut() {
        // Appel du repository pour gérer la déconnexion.
        repository.signOut()
        // Mise à jour de l'état de l'utilisateur à null après la déconnexion.
        _user.value = null
    }
}
