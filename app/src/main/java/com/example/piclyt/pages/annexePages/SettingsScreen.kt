package com.example.piclyt.pages.annexePages

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.piclyt.MainActivity.Companion.listMedias
import com.example.piclyt.utils.ProfileCard
import com.example.piclyt.utils.createBottomNavigation
import com.example.piclyt.utils.createHeaderText

// ########################## Ecran de paramètres ######################### //

// Fonction principale de l'écran de paramètres
@Composable
fun SettingsScreen(navController: NavController, context: Context, modifier: Modifier = Modifier) {
    createBottomNavigation(navController, context, modifier, true, listMedias) // Affichage de la barre de navigation

    Column {
        createHeaderText("Settings") // Affichage du titre de la page
        ProfileCard() // Décoration temporaire
    }
}