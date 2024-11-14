package com.filleuxstudio.appandroidcocktail.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.filleuxstudio.appandroidcocktail.R
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomepageScreen(
    onNavigateToList: () -> Unit,
    onNavigateToFirebase: () -> Unit
) {
    // Colors from the cocktail image
    val redColor = Color(0xFFFF4C4C) // Red from the cocktail
    val yellowColor = Color(0xFFFFE773) // Yellow
    val greenColor = Color(0xFFBEFA91) // Green
    val blackOutline = Color.Black

    // Récupérer l'utilisateur connecté
    val currentUser = FirebaseAuth.getInstance().currentUser
    val userId = currentUser?.uid

    // Background with circles in red, yellow, and green
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Green circle (top left)
        Box(
            modifier = Modifier
                .size(90.dp)
                .offset(x = (-50).dp, y = (-60).dp)
                .background(greenColor, shape = CircleShape)
        )

        // Red circle (top right)
        Box(
            modifier = Modifier
                .size(70.dp)
                .offset(x = (250).dp, y = (20).dp)
                .background(redColor, shape = CircleShape)
        )

        // Smaller yellow circle (middle)
        Box(
            modifier = Modifier
                .size(50.dp)
                .offset(x = (60).dp, y = (250).dp)
                .background(yellowColor, shape = CircleShape)
        )

        // Green circle (bottom left)
        Box(
            modifier = Modifier
                .size(80.dp)
                .offset(x = (-30).dp, y = (600).dp)
                .background(greenColor, shape = CircleShape)
        )

        // Red circle (bottom right)
        Box(
            modifier = Modifier
                .size(70.dp)
                .offset(x = (180).dp, y = (700).dp)
                .background(redColor, shape = CircleShape)
        )

        // Main content: image, text, and buttons
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Cocktail image
            Image(
                painter = painterResource(id = R.drawable.cocktail),
                contentDescription = "Cocktail Image",
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 16.dp),
                contentScale = ContentScale.Fit
            )

            // Welcome text
            Text(
                text = "Welcome to the Cocktail App",
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            if (userId != null) {
                // Affichage de l'ID utilisateur si l'utilisateur est connecté
                Text(
                    text = "Your ID : $userId",
                    fontWeight = FontWeight.Bold
                )
            } else {
                Text("Connect to retrieve your account ID")
            }

            // Button to navigate to API List
            Button(
                onClick = onNavigateToList,
                colors = ButtonDefaults.buttonColors(containerColor = redColor),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .width(220.dp)
                    .padding(bottom = 16.dp)
                    .border(2.dp, blackOutline, RoundedCornerShape(16.dp))
            ) {
                Text(
                    text = "Go to API List",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }

            // Button to navigate to Firebase Authentication
            Button(
                onClick = onNavigateToFirebase,
                colors = ButtonDefaults.buttonColors(containerColor = yellowColor),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .width(220.dp)
                    .border(2.dp, blackOutline, RoundedCornerShape(16.dp))
            ) {
                Text(
                    text = "Go to Firebase Auth",
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        // Bottom section to display team members' names
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Team Members: Dimitri, Noah, Kylian",
                color = Color.Gray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
