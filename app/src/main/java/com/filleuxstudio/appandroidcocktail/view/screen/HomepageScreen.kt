package com.filleuxstudio.appandroidcocktail.view.screen

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
import androidx.compose.ui.res.stringResource
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
    // Couleurs issues de l'image du cocktail
    val redColor = Color(0xFFFF4C4C) // Rouge du cocktail
    val yellowColor = Color(0xFFFFE773) // Jaune
    val greenColor = Color(0xFFBEFA91) // Vert
    val blackOutline = Color.Black

    // Récupérer l'utilisateur connecté
    val currentUser = FirebaseAuth.getInstance().currentUser
    val userId = currentUser?.uid

    // Arrière-plan avec des cercles colorés en rouge, jaune, et vert
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Cercle vert (coin supérieur gauche)
        Box(
            modifier = Modifier
                .size(90.dp)
                .offset(x = (-50).dp, y = (-60).dp)
                .background(greenColor, shape = CircleShape)
        )

        // Cercle rouge (coin supérieur droit)
        Box(
            modifier = Modifier
                .size(70.dp)
                .offset(x = (250).dp, y = (20).dp)
                .background(redColor, shape = CircleShape)
        )

        // Petit cercle jaune (milieu)
        Box(
            modifier = Modifier
                .size(50.dp)
                .offset(x = (60).dp, y = (250).dp)
                .background(yellowColor, shape = CircleShape)
        )

        // Cercle vert (coin inférieur gauche)
        Box(
            modifier = Modifier
                .size(80.dp)
                .offset(x = (-30).dp, y = (600).dp)
                .background(greenColor, shape = CircleShape)
        )

        // Cercle rouge (coin inférieur droit)
        Box(
            modifier = Modifier
                .size(70.dp)
                .offset(x = (180).dp, y = (700).dp)
                .background(redColor, shape = CircleShape)
        )

        // Contenu principal : image, texte, et boutons
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Image de cocktail
            Image(
                painter = painterResource(id = R.drawable.cocktail),
                contentDescription = "Cocktail Image",
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 16.dp),
                contentScale = ContentScale.Fit
            )

            // Texte de bienvenue
            Text(
                text = stringResource(R.string.title_home),
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            if (userId != null) {
                // Affichage de l'ID utilisateur si l'utilisateur est connecté
                Text(
                    text = stringResource(R.string.libelle_your_id) + "$userId",
                    fontWeight = FontWeight.Bold
                )
            } else {
                Text(stringResource(R.string.libelle_connect_to_retrieve_id))
            }

            // Bouton pour naviguer vers la liste API
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
                    text = stringResource(R.string.libelle_go_to_api_list),
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }

            // Bouton pour naviguer vers l'authentification Firebase
            Button(
                onClick = onNavigateToFirebase,
                colors = ButtonDefaults.buttonColors(containerColor = yellowColor),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .width(220.dp)
                    .border(2.dp, blackOutline, RoundedCornerShape(16.dp))
            ) {
                Text(
                    text = stringResource(R.string.libelle_go_to_firebase_auth),
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        // Section en bas pour afficher les noms des membres de l'équipe
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Team Members : Dimitri, Noah, Kylian",
                color = Color.Gray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
