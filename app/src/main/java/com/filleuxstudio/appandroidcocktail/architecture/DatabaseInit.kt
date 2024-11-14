package com.filleuxstudio.appandroidcocktail.architecture

import android.app.Application
import androidx.room.Room

// Classe pour initialiser la base de données Room dans l'application
class DatabaseInit : Application() {

    // Companion object pour fournir un accès à l'instance de DatabaseInit à travers l'application
    companion object {
        // Variable d'instance globale pour accéder à cette classe
        lateinit var instance: DatabaseInit
    }

    // Propriété pour initialiser la base de données
    val mApplicationDatabase: RoomDatabaseCocktail by lazy {
        // Construction de la base de données
        Room.databaseBuilder(
            applicationContext,
            RoomDatabaseCocktail::class.java,
            "DatabaseCocktail" // Nom de la base de données
        ).fallbackToDestructiveMigration() // Destruction et recréation de la base de données en cas de modification du schéma
            .build() // Construit l'instance de la base de données
    }

    // Fonction appelée lorsque l'application est créée
    override fun onCreate() {
        super.onCreate()
        // Initialisation de l'instance globale
        instance = this
    }
}
