package com.filleuxstudio.appandroidcocktail.view.screen

import android.util.Log
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.filleuxstudio.appandroidcocktail.R
import com.filleuxstudio.appandroidcocktail.data.model.IngredientObject
import com.filleuxstudio.appandroidcocktail.view.viewmodel.IngredientViewModel
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import kotlinx.coroutines.launch

@Composable
fun APIListViewScreen(navController: NavController) {
    val viewModel: IngredientViewModel = viewModel()
    val list = viewModel.ingredient.collectAsState().value
    var errorMessage by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xf2f2f2ff))
                .padding(16.dp)
        ) {
           /* SearchBar(viewModel) { query ->
                viewModel.insertAllIngredient(query) { message ->
                    errorMessage = message
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            }*/
            SearchBar(viewModel) { query ->
                viewModel.insertAllIngredient(query) { message ->
                    errorMessage = message
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            IngredientList(Modifier.padding(padding), list)
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun SearchBar(viewModel: IngredientViewModel, onSearch: (String) -> Unit) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    var errorMessage by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column {
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }

    Box(
        modifier = Modifier
            .background(
                Color(0xffffffff),
                shape = MaterialTheme.shapes.medium
            ) // Couleur de fond appliquée d'abord
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        BasicTextField(
            value = searchQuery,
            /*onValueChange = { newValue ->
                searchQuery = newValue
            },*/
            onValueChange = {
                searchQuery = it
                if (it.text.length > 2) {
                    //viewModel.searchCocktails(it)
                    onSearch(it.text)// Rechercher des cocktails si le champ n'est pas vide
                }
            },
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_search_24),
                        contentDescription = "Search Icon",
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    if (searchQuery.text.isEmpty()) {
                        Text("Search ingredients...", color = Color.Black, fontSize = 18.sp)
                    }
                    innerTextField()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                /*.onKeyEvent { keyEvent ->
                    if (keyEvent.key == Key.Enter) {
                        // Appeler la fonction de recherche lorsque "Entrée" est pressée
                        onSearch(searchQuery.text)
                        focusManager.clearFocus()
                        //viewModel.insertAllIngredient()
                        true // Indiquer que l'événement a été consommé
                    } else {
                        false
                    }
                }*/
        )
    }
}


@Composable
fun IngredientList(modifier: Modifier, listOfResult: List<IngredientObject>) {
    var selectedIngredient by remember { mutableStateOf<IngredientObject?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    val distinctIngredients = listOfResult.distinct() // Eliminer les doublons

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 16.dp)
    ) {
        items(distinctIngredients, key = { it.nameIngredient }) { item -> // Assurez-vous d'utiliser une clé unique
            IngredientItem(ingredient = item) {
                selectedIngredient = item
                showDialog = true
            }
        }
    }

    if (showDialog && selectedIngredient != null) {
        IngredientDetailDialog(ingredient = selectedIngredient!!) {
            showDialog = false
        }
    }
}

@Composable
fun IngredientItem(ingredient:  IngredientObject, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .background(Color(0xFFFFFFFF))
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = getIngredientImageResource(ingredient.nameIngredient)),
                contentDescription = ingredient.nameIngredient,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = ingredient.nameIngredient,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun IngredientDetailDialog(ingredient: IngredientObject, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = ingredient.nameIngredient) },
        text = {
            Column {
                Text(text = "Description: ${ingredient.description}")
                Text(text = "Type: ${ingredient.type}")
                Text(text = "Is Alcoholic: ${ingredient.isAlcohol}")
                Text(text = "ABV: ${ingredient.abv}%")
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}

@DrawableRes
fun getIngredientImageResource(name: String): Int {
    return when {
        name.startsWith("wh", ignoreCase = true) -> R.drawable.whiskey
        name.startsWith("w", ignoreCase = true) -> R.drawable.wine
        name.startsWith("r", ignoreCase = true) -> R.drawable.rum
        name.startsWith("b", ignoreCase = true) -> R.drawable.beer
        name.startsWith("j", ignoreCase = true) -> R.drawable.jager
        name.startsWith("ch", ignoreCase = true) -> R.drawable.champagne
        name.startsWith("so", ignoreCase = true) -> R.drawable.soda
        name.startsWith("t", ignoreCase = true) -> R.drawable.tonic
        name.startsWith("g", ignoreCase = true) -> R.drawable.gin
        // Ajoutez d'autres règles si nécessaire
        else -> R.drawable.liquor // Image par défaut si aucun cas ne correspond
    }
}

/*@Composable
fun BottomNavigationBar(navController: NavController) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xffffffff))
            .padding(10.dp),  // Ajoutez un peu de remplissage pour le style
        horizontalArrangement = Arrangement.SpaceAround,  // Espacement entre les boutons
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Bouton pour naviguer vers l'écran Home
        Button(
            onClick = { navController.navigate(("homepage")) },
            shape = RoundedCornerShape(5.dp), // Rayon de bordure de 5 dp
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4C4C))
        ) {
            Text(text = "Home", color = Color.Black)
        }

        // Bouton pour naviguer vers l'écran Cocktails
        Button(
            onClick = { navController.navigate("cocktails") },
            shape = RoundedCornerShape(5.dp), // Rayon de bordure de 5 dp
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFF00))
        ) {
            Text(text = "Cocktails", color = Color.Black)
        }

        // Bouton pour naviguer vers l'écran Profile
        Button(
            onClick = { navController.navigate("random")},
            shape = RoundedCornerShape(5.dp), // Rayon de bordure de 5 dp
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB0FF00))
        ) {
            Text(text = "Random", color = Color.Black)
        }
    }
}*/

@Composable
fun BottomNavigationBar(navController: NavController) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xffffffff))
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem(
            iconResId = R.drawable.home,
            label = "Home",
            route = "homepage",
            color = Color(0xFFFF4C4C),
            navController = navController,
            currentRoute = currentRoute
        )

        BottomNavItem(
            iconResId = R.drawable.cocktailshaker,
            label = "Ingredients",
            route = "listFeature",
            color = Color(0xFFFF4C4C),
            navController = navController,
            currentRoute = currentRoute
        )

        BottomNavItem(
            iconResId  = R.drawable.cocktails,
            label = "Cocktails",
            route = "cocktails",
            color = Color(0xFFFFFF00),
            navController = navController,
            currentRoute = currentRoute
        )

        BottomNavItem(
            iconResId  = R.drawable.drink,
            label = "Random",
            route = "random",
            color = Color(0xFFB0FF00),
            navController = navController,
            currentRoute = currentRoute
        )
    }
}

@Composable
fun BottomNavItem(
    @DrawableRes iconResId: Int,
    label: String,
    route: String,
    color: Color,
    navController: NavController,
    currentRoute: String?
) {
    val selected = currentRoute == route
    val background = if (selected) color.copy(alpha = 0.1f) else Color.Transparent

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(background)
            .clickable(onClick = {
                navController.navigate(route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            })
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = label,
            modifier = Modifier.size(24.dp),
            colorFilter = ColorFilter.tint(if (selected) color else Color.Gray)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            color = if (selected) color else Color.Gray,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1
        )
    }
}
