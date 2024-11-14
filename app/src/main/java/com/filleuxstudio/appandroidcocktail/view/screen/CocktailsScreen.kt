package com.filleuxstudio.appandroidcocktail.view.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.filleuxstudio.appandroidcocktail.R
import com.filleuxstudio.appandroidcocktail.data.model.CocktailObject
import com.filleuxstudio.appandroidcocktail.view.viewmodel.CocktailViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CocktailsScreen(navController: NavController) {
    val viewModel: CocktailViewModel = viewModel()
    val cocktails by viewModel.cocktails.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Barre de recherche avec champ de texte et bouton de suppression
                SearchBar(viewModel, modifier = Modifier.weight(1f))
                DeleteAllButton(viewModel)
            }
            // Affichage de la liste des cocktails
            CocktailList(cocktails)
        }
    }
}

@Composable
fun DeleteAllButton(viewModel: CocktailViewModel) {
    // Bouton pour supprimer tous les cocktails de la liste
    Button(
        onClick = { viewModel.deleteAllCocktails() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            contentColor = Color.White
        ),
        modifier = Modifier.padding(start = 8.dp)
    ) {
        Text(stringResource(R.string.delete_all_button_text))
    }
}

@Composable
fun SearchBar(viewModel: CocktailViewModel, modifier: Modifier = Modifier) {
    var searchQuery by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .background(Color.White, shape = MaterialTheme.shapes.medium)
            .padding(8.dp)
    ) {
        BasicTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                if (it.isNotEmpty()) {
                    viewModel.searchCocktails(it) // Rechercher des cocktails si le champ n'est pas vide
                } else {
                    viewModel.loadDefaultCocktails() // Charger les cocktails par défaut si le champ est vide
                }
            },
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
            decorationBox = { innerTextField ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Icône de recherche
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_search_24),
                        contentDescription = stringResource(R.string.search_icon_description),
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    if (searchQuery.isEmpty()) {
                        Text(stringResource(R.string.search_bar_hint), color = Color.Gray, fontSize = 18.sp)
                    }
                    innerTextField()
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun CocktailList(cocktails: List<CocktailObject>) {
    var selectedCocktail by remember { mutableStateOf<CocktailObject?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    // Regroupement des cocktails par la première lettre de leur nom
    val groupedCocktails = cocktails.groupBy { cocktail ->
        cocktail.nameDrink.firstOrNull()?.toString() ?: "#"
    }

    LazyColumn {
        groupedCocktails.forEach { (letter, cocktailList) ->
            item { HeaderItem(letter) } // Affichage de l'en-tête pour chaque groupe

            items(cocktailList) { cocktail ->
                CocktailItem(cocktail) {
                    selectedCocktail = cocktail
                    showDialog = true // Afficher les détails du cocktail au clic
                }
            }
        }

        item { FooterItem() } // Ajouter un pied de page à la fin de la liste
    }

    // Affichage du dialogue de détail si un cocktail est sélectionné
    if (showDialog && selectedCocktail != null) {
        CocktailDetailDialog(cocktail = selectedCocktail!!) {
            showDialog = false
        }
    }
}

@Composable
fun HeaderItem(letter: String) {
    // En-tête pour chaque groupe de cocktails, basé sur la première lettre
    Text(
        text = letter,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(vertical = 8.dp, horizontal = 16.dp)
    )
}

@Composable
fun FooterItem() {
    // Pied de page pour indiquer la fin de la liste
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .padding(vertical = 16.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.footer_text),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )
    }
}

@Composable
fun CocktailItem(cocktail: CocktailObject, onClick: () -> Unit) {
    // Élément de la liste de cocktails avec image, nom, et clic pour voir les détails
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White, shape = MaterialTheme.shapes.medium)
            .padding(16.dp)
            .clickable(onClick = onClick)
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_list),
            contentDescription = cocktail.nameDrink,
            modifier = Modifier.size(48.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))
        Text(text = cocktail.nameDrink, fontSize = 18.sp)
    }
}

@Composable
fun CocktailDetailDialog(cocktail: CocktailObject, onDismiss: () -> Unit) {
    // Dialogue pour afficher les détails d'un cocktail sélectionné
    AlertDialog(
        onDismissRequest = onDismiss,
        modifier = Modifier.fillMaxWidth(0.9f),
        title = { Text(text = cocktail.nameDrink) },
        text = {
            Column {
                AsyncImage(
                    model = cocktail.drinkThumb,
                    contentDescription = "Cocktail Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Category: ${cocktail.category}")
                Text(text = "Alcoholic: ${cocktail.tags}")
                Text(text = "Glass: ${cocktail.glass}")
                Text(text = "Instructions: ${cocktail.intructions}")
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.dialog_close_button))
            }
        }
    )
}
