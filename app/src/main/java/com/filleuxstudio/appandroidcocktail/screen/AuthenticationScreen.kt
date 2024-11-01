package com.filleuxstudio.appandroidcocktail.screen

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.R
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.filleuxstudio.appandroidcocktail.viewmodel.AuthViewModel
import com.filleuxstudio.appandroidcocktail.architecture.AuthViewModelFactory
import com.filleuxstudio.appandroidcocktail.repository.FirebaseAuthRepository
import com.filleuxstudio.appandroidcocktail.utils.Secrets
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AuthenticationScreen(navController: NavController) {
    val repository = FirebaseAuthRepository(FirebaseAuth.getInstance())
    val viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(repository))
    AuthenticationScreenContent(viewModel = viewModel, navController = navController)
}

@Composable
fun AuthenticationScreenContent(viewModel: AuthViewModel, navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val errorMessage by viewModel.errorMessage.collectAsState()
    val user by viewModel.user.collectAsState()

    val context = LocalContext.current as Activity

    // Configuration Google Sign- en utilisant l'ID client de Secrets.kt
    val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(Secrets.GOOGLE_CLIENT_ID)
        .requestEmail()
        .build()

    val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions)


    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.result
                if (account != null) {
                    println("GoogleSignInAccount reçu : ${account.email}")
                    viewModel.signInWithGoogle(account)
                } else {
                    println("GoogleSignInAccount est null")
                }
            } catch (e: ApiException) {
                println("Erreur ApiException lors de la tentative de connexion via Google : code=${e.statusCode}")
                e.printStackTrace()
            } catch (e: Exception) {
                println("Erreur générique lors de la tentative de connexion via Google : ${e.localizedMessage}")
                e.printStackTrace()
            }
        } else {
            println("GoogleSignIn a échoué ou a été annulé")
        }
    }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header avec flèche de retour
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
                text = "Connexion",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (user == null) {
            // Formulaire de connexion
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
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    if (errorMessage != null) {
                        Text(
                            text = errorMessage!!,
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    Button(
                        onClick = {
                            viewModel.signIn(email, password)
                            if (viewModel.user.value != null) {
                                val userId = viewModel.user.value?.uid ?: ""
                                navController.navigate("homepage/$userId")
                            }
                        },
                        modifier = Modifier
                            .padding(vertical = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF4C4C)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = "Login", color = Color.White)
                    }

                    TextButton(onClick = { navController.navigate("signup") }) {
                        Text("Not yet registered? Click here to register!", color = Color(0xFFFF4C4C))
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Bouton pour se connecter avec Google
                    Button(
                        onClick = {
                            val signInIntent = googleSignInClient.signInIntent
                            launcher.launch(signInIntent)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4285F4)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = "Se connecter avec Google", color = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        } else {
            // Message pour utilisateur connecté
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Welcome, logged-in user : ${user?.uid}", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    viewModel.signOut()
                    navController.navigate("firebaseFeature")
                }) {
                    Text("Logout")
                }
            }
        }
    }
}
