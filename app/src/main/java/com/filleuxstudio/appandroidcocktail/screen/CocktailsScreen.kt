package com.filleuxstudio.appandroidcocktail.screen

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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.filleuxstudio.appandroidcocktail.R
import com.filleuxstudio.appandroidcocktail.data.model.CocktailObject
import com.filleuxstudio.appandroidcocktail.viewmodel.CocktailViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CocktailsScreen() {
    val viewModel: CocktailViewModel = viewModel()
    val cocktails by viewModel.cocktails.collectAsState()

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            SearchBar(viewModel)
            Spacer(modifier = Modifier.height(16.dp))
            CocktailList(cocktails)
        }
    }
}

@Composable
fun SearchBar(viewModel: CocktailViewModel) {
    var searchQuery by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .background(Color.White, shape = MaterialTheme.shapes.medium)
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        BasicTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                if (it.isNotEmpty()) {
                    viewModel.searchCocktails(it)
                } else {
                    viewModel.loadDefaultCocktails() // Reset to default cocktails when search is cleared
                }
            },
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
            decorationBox = { innerTextField ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_search_24),
                        contentDescription = "Search Icon",
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    if (searchQuery.isEmpty()) {
                        Text("Search cocktails...", color = Color.Gray, fontSize = 18.sp)
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

    LazyColumn {
        items(cocktails) { cocktail ->
            CocktailItem(cocktail) {
                selectedCocktail = cocktail
                showDialog = true
            }
        }
    }

    if (showDialog && selectedCocktail != null) {
        CocktailDetailDialog(cocktail = selectedCocktail!!) {
            showDialog = false
        }
    }
}

@Composable
fun CocktailItem(cocktail: CocktailObject, onClick: () -> Unit) {
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
    AlertDialog(
        onDismissRequest = onDismiss,
        modifier = Modifier.fillMaxWidth(0.9f), // Set width to occupy more of the screen
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
                Text("Close")
            }
        }
    )
}
