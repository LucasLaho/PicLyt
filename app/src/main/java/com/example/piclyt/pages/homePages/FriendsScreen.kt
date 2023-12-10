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
import com.example.piclyt.utils.createBottomNavigation
import com.example.piclyt.utils.createHeaderText

// ########################## Ecran d'Amis ######################### //
// Utilité : Ici, on pourra voir les albums publics de nos amis.

// Fonction principale de l'écran d'Amis où on retrouvera les albums publics de nos amis
@Composable
fun FriendsScreen(navController: NavController, context: Context, modifier: Modifier = Modifier) {
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
            createHeaderText("Amis") // Affichage du titre
        }
    }
}