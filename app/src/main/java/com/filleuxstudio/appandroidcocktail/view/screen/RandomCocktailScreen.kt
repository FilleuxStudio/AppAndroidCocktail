package com.filleuxstudio.appandroidcocktail.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.filleuxstudio.appandroidcocktail.view.viewmodel.RandomCocktailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomCocktailScreen(navController: NavController, viewModel: RandomCocktailViewModel = viewModel()) {
    // Structure générale de l'interface avec une barre de navigation supérieure et inférieure
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Random Cocktail", color = Color.Black) },
                actions = {
                    // Bouton pour générer un cocktail aléatoire
                    Button(
                        onClick = { viewModel.fetchRandomCocktail() },
                        modifier = Modifier
                            .padding(8.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 4.dp,  // Élévation par défaut
                            pressedElevation = 1.dp,  // Élévation lors de la pression du bouton
                            hoveredElevation = 6.dp  // Élévation au survol (sur plateformes de bureau)
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFBEFA91)
                        )
                    ) {
                        Text(text = "Random", color = Color.Black)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xf2f2f2ff)
                )
            )
        },
        bottomBar = {
            // Barre de navigation en bas de l'écran
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->
        // Contenu principal de l'écran
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xf2f2f2ff))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Observations des états de l'application (cocktail actuel et message d'erreur)
            val cocktail by viewModel.currentCocktail.collectAsState()
            val errorMessage by viewModel.errorMessage.collectAsState()

            // Affichage basé sur l'état des données (erreur, cocktail, ou message par défaut)
            when {
                errorMessage != null -> {
                    // Affiche un message d'erreur si un problème est détecté
                    Text(
                        text = errorMessage ?: "Unknown error",
                        color = Color.Red,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                cocktail != null -> {
                    // Affiche les informations du cocktail si disponibles
                    Text(
                        text = cocktail!!.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Category: ${cocktail!!.category ?: "Unknown"}",
                        fontSize = 18.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    // Image du cocktail
                    AsyncImage(
                        model = cocktail!!.imageUrl,
                        contentDescription = "Cocktail Image",
                        modifier = Modifier.size(200.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    // Instructions pour préparer le cocktail
                    Text(
                        text = "Instructions: ${cocktail!!.instructions ?: "No instructions"}",
                        fontSize = 16.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Justify
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    // Liste des ingrédients
                    Text(
                        text = "Ingredients:",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    )
                    // Affichage des détails des ingrédients et mesures associées
                    cocktail!!.ingredients.zip(cocktail!!.measures).forEach { (ingredient, measure) ->
                        Text(text = "$ingredient: $measure", color = Color.Gray)
                    }
                }
                else -> {
                    // Message par défaut en cas d'absence de données
                    Text(text = "Press 'Random' to load a cocktail", color = Color.Gray)
                }
            }
        }
    }
}

