package com.filleuxstudio.appandroidcocktail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.filleuxstudio.appandroidcocktail.view.navigation.AppNavigation
import com.filleuxstudio.appandroidcocktail.ui.theme.AppAndroidCocktailTheme

// Classe principale de l'application
class MainActivity : ComponentActivity() {
    // Fonction appelée lors de la création de l'activité
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Définit le thème de l'application et lance le composant principal
            AppAndroidCocktailTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    // Initialise le contrôleur de navigation
    val navController = rememberNavController()
    // Lance la navigation de l'application en utilisant le contrôleur de navigation
    AppNavigation(navController = navController)
}
