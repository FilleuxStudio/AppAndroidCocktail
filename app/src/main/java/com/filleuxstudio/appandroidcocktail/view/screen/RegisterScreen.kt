package com.filleuxstudio.appandroidcocktail.view.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.filleuxstudio.appandroidcocktail.R
import com.filleuxstudio.appandroidcocktail.view.viewmodel.AuthViewModel
import com.filleuxstudio.appandroidcocktail.architecture.AuthViewModelFactory
import com.filleuxstudio.appandroidcocktail.data.FirebaseAuthRepository
import com.google.firebase.auth.FirebaseAuth

@Composable
fun RegisterScreen(navController: NavController) {
    // Création du repository et du ViewModel d'authentification
    val repository = FirebaseAuthRepository(FirebaseAuth.getInstance())
    val viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(repository))

    // Appel du contenu de l'écran d'inscription
    RegisterScreenContent(viewModel = viewModel, navController = navController)
}

@Composable
fun RegisterScreenContent(viewModel: AuthViewModel, navController: NavController) {
    // Variables pour stocker les entrées utilisateur
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Observer l'état de l'utilisateur et des erreurs
    val user by viewModel.user.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    // Contexte pour afficher les Toasts
    val context = LocalContext.current

    // Affichage d'un Toast si l'utilisateur est connecté
    LaunchedEffect(user) {
        if (user != null) {
            Toast.makeText(context, "Registration successful!", Toast.LENGTH_SHORT).show()

            // Navigation optionnelle après l'inscription
            navController.navigate("homepage")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // En-tête avec flèche de retour
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.navigate("homepage") }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Retour")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.screen_title_register),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Formulaire d'inscription
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Champ de saisie de l'email
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(12.dp))
                // Champ de saisie du mot de passe
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(stringResource(R.string.label_password)) },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Affichage du message d'erreur si présent
                if (errorMessage != null) {
                    Text(
                        text = errorMessage!!,
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // Bouton d'inscription
                Button(
                    onClick = { viewModel.signUp(email, password) }, // Appelle la méthode d'inscription
                    modifier = Modifier
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4C4C)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = stringResource(R.string.screen_title_register), color = Color.White)
                }

                // Lien pour la connexion si l'utilisateur a déjà un compte
                TextButton(onClick = { navController.navigate("firebaseFeature") }) {
                    Text(stringResource(R.string.link_already_have_account), color = Color(0xFFFF4C4C))
                }
            }
        }
    }
}
