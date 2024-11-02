package com.filleuxstudio.appandroidcocktail.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.filleuxstudio.appandroidcocktail.screen.AuthenticationScreen
import com.filleuxstudio.appandroidcocktail.screen.APIListViewScreen
import com.filleuxstudio.appandroidcocktail.screen.HomepageScreen
import com.filleuxstudio.appandroidcocktail.screen.RegisterScreen
import com.filleuxstudio.appandroidcocktail.screen.CocktailsScreen
import com.filleuxstudio.appandroidcocktail.screen.RandomCocktailScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = "homepage") {
        composable("homepage") {
            HomepageScreen(
                onNavigateToList = { navController.navigate("listFeature") },
                onNavigateToFirebase = { navController.navigate("firebaseFeature") }
            )
        }
        composable("listFeature") {
            APIListViewScreen(navController)
        }
        composable("firebaseFeature") {
            AuthenticationScreen(navController)
        }
        composable("signup") {
            RegisterScreen(navController)
        }
        composable("cocktails") {
            CocktailsScreen() // Do not pass navController here if not needed
        }
        composable("random"){
            RandomCocktailScreen(navController)
        }
    }
}
