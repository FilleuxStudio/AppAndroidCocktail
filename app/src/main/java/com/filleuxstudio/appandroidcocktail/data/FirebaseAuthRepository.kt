package com.filleuxstudio.appandroidcocktail.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

// Repository pour gérer l'authentification avec Firebase
class FirebaseAuthRepository(private val auth: FirebaseAuth) {

    // Méthode pour inscrire un utilisateur avec un email et un mot de passe
    suspend fun signUp(email: String, password: String): Result<FirebaseUser?> {
        return try {
            // Création de l'utilisateur avec Firebase
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            Result.success(result.user) // Retourne l'utilisateur en cas de succès
        } catch (e: FirebaseAuthException) {
            Result.failure(e) // Retourne une erreur en cas d'échec
        }
    }

    // Méthode pour connecter un utilisateur avec un email et un mot de passe
    suspend fun signIn(email: String, password: String): Result<FirebaseUser?> {
        return try {
            // Connexion de l'utilisateur avec Firebase et attente de la tâche
            val result = auth.signInWithEmailAndPassword(email, password).await()
            Result.success(result.user) // Retourne l'utilisateur en cas de succès
        } catch (e: FirebaseAuthException) {
            Result.failure(e) // Retourne une erreur en cas d'échec
        }
    }

    // Méthode pour déconnecter l'utilisateur actuellement connecté
    fun signOut() {
        auth.signOut()
    }

    // Méthode pour obtenir l'utilisateur actuellement connecté
    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }
}
