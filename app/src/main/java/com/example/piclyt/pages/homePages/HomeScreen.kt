package com.example.piclyt.pages.homePages

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.piclyt.data.MediaModel
import com.example.piclyt.fireBaseUtils.AuthManager
import com.example.piclyt.utils.AlbumSection
import com.example.piclyt.utils.GreetingSection
import com.example.piclyt.utils.createBottomNavigation
import com.example.piclyt.utils.createHeaderText

// ########################## Ecran d'accueil ######################### //

// Fonction principale de l'écran d'accueil
@Composable
fun HomeScreen(navController: NavController, context: Context, authManager: AuthManager, modifier: Modifier = Modifier, viewModel: MediaModel) {
    createBottomNavigation(navController, context, modifier, true, viewModel) // Affichage de la barre de navigation

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
            GreetingSection("Thierry") // Affichage du message de bienvenu
            AlbumSection(navController) // Affichage des albums sur la page d'accueil. Bug encore
        }
    }
}