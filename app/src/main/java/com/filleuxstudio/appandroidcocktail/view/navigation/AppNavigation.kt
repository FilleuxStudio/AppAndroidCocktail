package com.filleuxstudio.appandroidcocktail.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.filleuxstudio.appandroidcocktail.view.screen.AuthenticationScreen
import com.filleuxstudio.appandroidcocktail.view.screen.APIListViewScreen
import com.filleuxstudio.appandroidcocktail.view.screen.HomepageScreen
import com.filleuxstudio.appandroidcocktail.view.screen.RegisterScreen
import com.filleuxstudio.appandroidcocktail.view.screen.CocktailsScreen
import com.filleuxstudio.appandroidcocktail.view.screen.RandomCocktailScreen

// Composable pour gérer la navigation de l'application
@Composable
fun AppNavigation(navController: NavHostController) {
    // Configuration du NavHost pour gérer les destinations de navigation
    NavHost(navController, startDestination = "homepage") {

        // Définition de la route "homepage"
        composable("homepage") {
            HomepageScreen(
                // Navigation vers les autres fonctionnalités à partir de l'écran d'accueil
                onNavigateToList = { navController.navigate("listFeature") },
                onNavigateToFirebase = { navController.navigate("firebaseFeature") }
            )
        }

        // Définition de la route "listFeature"
        composable("listFeature") {
            APIListViewScreen(navController) // Affiche l'écran de la liste des API
        }

        // Définition de la route "firebaseFeature"
        composable("firebaseFeature") {
            AuthenticationScreen(navController) // Affiche l'écran d'authentification
        }

        // Définition de la route "signup"
        composable("signup") {
            RegisterScreen(navController) // Affiche l'écran d'enregistrement
        }

        // Définition de la route "cocktails"
        composable("cocktails") {
            CocktailsScreen(navController) // Affiche l'écran des cocktails
        }

        // Définition de la route "random"
        composable("random") {
            RandomCocktailScreen(navController) // Affiche l'écran pour un cocktail aléatoire
        }
    }
}
