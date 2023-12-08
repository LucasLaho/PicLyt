package com.example.piclyt.pages.homePages

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.piclyt.MainActivity.Companion.listMedias
import com.example.piclyt.utils.createBottomNavigation
import com.example.piclyt.utils.createHeaderText

// ########################## Ecran d'ajout d'album ######################### //

// Fonction principale de la page d'ajout d'album
@Composable
fun AddScreen(navController: NavController, context: Context, modifier: Modifier = Modifier) {
    createBottomNavigation(navController, context, modifier, true, listMedias) // Affichage de la barre de navigation
    createHeaderText("Ajout d'un album") // Affichage du titre de la page
}
