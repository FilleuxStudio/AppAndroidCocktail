package com.filleuxstudio.appandroidcocktail.architecture

import android.app.Application
import androidx.room.Room

class DatabaseInit: Application() {
    companion object {
        lateinit var instance: DatabaseInit
    }

    val mApplicationDatabase: RoomDatabaseCocktail by lazy {
        Room.databaseBuilder(
            applicationContext,
            RoomDatabaseCocktail::class.java,
            "DatabaseCocktail"
        ).fallbackToDestructiveMigration().build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}