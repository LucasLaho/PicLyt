package com.example.piclyt.pages.homePages

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.piclyt.utils.ImageViewModel
import com.example.piclyt.utils.createBottomNavigation
import com.example.piclyt.utils.createHeaderText
import com.google.firebase.auth.FirebaseAuth

// ########################## Ecran d'ajout d'album ######################### //

// Fonction principale de la page d'ajout d'album
@Composable
fun AddScreen(navController: NavController, context: Context, auth: FirebaseAuth, modifier: Modifier = Modifier, viewModel: ImageViewModel) {
    createBottomNavigation(navController, context, modifier, true, viewModel) // Affichage de la barre de navigation
    createHeaderText("Ajout d'un album") // Affichage du titre de la page
}
