package com.example.piclyt.pages.homePages

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.piclyt.MainActivity.Companion.authManager
import com.example.piclyt.MainActivity.Companion.listMedias
import com.example.piclyt.fireBaseUtils.getUsername
import com.example.piclyt.utils.AlbumSection
import com.example.piclyt.utils.GreetingSection
import com.example.piclyt.utils.createBottomNavigation
import com.example.piclyt.utils.createHeaderText
import com.google.firebase.firestore.FirebaseFirestore

// ########################## Ecran d'accueil ######################### //

// Fonction principale de l'écran d'accueil
@Composable
fun HomeScreen(navController: NavController, context: Context, db: FirebaseFirestore, modifier: Modifier = Modifier) {
    var username by remember { mutableStateOf("") }
    createBottomNavigation(navController, context, modifier, true) // Affichage de la barre de navigation

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp) // Ajustement de l'espace en fonction de la hauteur du BottomNavigation
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .fillMaxWidth()
        ) {
            createHeaderText("Albums") // Affichage du titre
            getUsername(authManager, db) { resultUsername ->
                username = resultUsername
            }
            GreetingSection(username) // Affichage du message de bienvenue
            AlbumSection(navController) // Affichage des albums sur la page d'accueil. Bug encore
        }
    }
}