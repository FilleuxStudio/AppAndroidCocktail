package com.filleuxstudio.appandroidcocktail.screen

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
import com.filleuxstudio.appandroidcocktail.viewmodel.RandomCocktailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomCocktailScreen(navController: NavController, viewModel: RandomCocktailViewModel = viewModel()) {
    // Structure générale de l'interface avec un haut et bas de page
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Random Cocktail",  color = Color.Black ) },
                actions = {
                    // Bouton pour générer un cocktail aléatoire
                    Button(
                        onClick = { viewModel.fetchRandomCocktail() },
                        modifier = Modifier
                            .padding(8.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 4.dp,  // Élévation par défaut
                            pressedElevation = 1.dp,  // Élévation quand le bouton est pressé
                            hoveredElevation = 6.dp  // Élévation quand la souris survole (pour les plateformes de bureau)
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
            // Barre de navigation en bas
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
            // Observations des états
            val cocktail by viewModel.currentCocktail.collectAsState()
            val errorMessage by viewModel.errorMessage.collectAsState()

            // Affichage basé sur l'état des données
            when {
                errorMessage != null -> {
                    // Affichage d'un message d'erreur si présent
                    Text(
                        text = errorMessage ?: "Unknown error",
                        color = Color.Red,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                cocktail != null -> {
                    // Affichage des informations du cocktail si disponible
                    Text(
                        text = cocktail!!.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        //color = MaterialTheme.colorScheme.primary,
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
                    AsyncImage(
                        model = cocktail!!.imageUrl,
                        contentDescription = "Cocktail Image",
                        modifier = Modifier.size(200.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Instructions: ${cocktail!!.instructions ?: "No instructions"}",
                        fontSize = 16.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Justify
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Ingredients:",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                    )
                    // Affichage des détails du cocktail
                    cocktail!!.ingredients.zip(cocktail!!.measures).forEach { (ingredient, measure) ->
                        Text(text = "$ingredient: $measure", color = Color.Gray)
                    }
                }
                else -> {
                    // Message par défaut si aucune donnée n'est disponible
                    Text(text = "Press 'Random' to load a cocktail", color = Color.Gray)
                }
            }
        }
    }
}
