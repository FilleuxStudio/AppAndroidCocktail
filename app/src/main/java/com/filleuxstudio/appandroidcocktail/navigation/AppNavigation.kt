package com.filleuxstudio.appandroidcocktail.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.filleuxstudio.appandroidcocktail.screen.HomepageScreen

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
            // Placeholder for API List (Feature 2)
        }
        composable("firebaseFeature") {
            // Placeholder for Firebase Authentication (Feature 3)
        }
    }
}
