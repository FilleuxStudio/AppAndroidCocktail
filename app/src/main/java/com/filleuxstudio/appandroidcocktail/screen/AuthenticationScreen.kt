package com.filleuxstudio.appandroidcocktail.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.filleuxstudio.appandroidcocktail.viewmodel.AuthViewModel
import com.filleuxstudio.appandroidcocktail.architecture.AuthViewModelFactory
import com.filleuxstudio.appandroidcocktail.data.FirebaseAuthRepository
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AuthenticationScreen() {
    val repository = FirebaseAuthRepository(FirebaseAuth.getInstance())
    val viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(repository))
    AuthenticationScreenContent(viewModel = viewModel)
}

@Composable
fun AuthenticationScreenContent(viewModel: AuthViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val errorMessage by viewModel.errorMessage.collectAsState()
    val user by viewModel.user.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (user == null) {
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Mot de passe") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (errorMessage != null) {
                Text(text = errorMessage!!, color = Color.Red)
                Spacer(modifier = Modifier.height(8.dp))
            }
            Row {
                Button(onClick = {
                    viewModel.signUp(email, password)
                }) {
                    Text("S'inscrire")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    viewModel.signIn(email, password)
                }) {
                    Text("Se connecter")
                }
            }
        } else {
            Text("Utilisateur connecté : ${user?.uid}")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                viewModel.signOut()
            }) {
                Text("Se déconnecter")
            }
        }
    }
}
