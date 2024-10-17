package com.filleuxstudio.appandroidcocktail.architecture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.filleuxstudio.appandroidcocktail.data.FirebaseAuthRepository
import com.filleuxstudio.appandroidcocktail.viewmodel.AuthViewModel

class AuthViewModelFactory(
    private val repository: FirebaseAuthRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
