package com.filleuxstudio.appandroidcocktail.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.filleuxstudio.appandroidcocktail.viewmodel.RandomCocktailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomCocktailScreen(navController: NavController,
    viewModel: RandomCocktailViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Random Cocktail") },
                actions = {
                    Button(
                        onClick = { viewModel.fetchRandomCocktail() },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "Random")
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->
        // Le contenu principal de votre écran
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val cocktail by viewModel.currentCocktail.collectAsState()
            val errorMessage by viewModel.errorMessage.collectAsState()

            when {
                errorMessage != null -> Text(
                    text = errorMessage ?: "Unknown error",
                    color = Color.Red,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                cocktail != null -> {
                    Text(
                        text = cocktail!!.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
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
                        textAlign = TextAlign.Justify
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Ingredients:",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    cocktail!!.ingredients.zip(cocktail!!.measures).forEach { (ingredient, measure) ->
                        BasicText(text = "$ingredient: $measure")
                    }
                }
                else -> Text(text = "Press 'Random' to load a cocktail", color = Color.Gray)
            }

        }


    }
}
