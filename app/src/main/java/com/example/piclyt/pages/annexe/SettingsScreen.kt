package com.example.piclyt.pages.annexe

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.piclyt.ui.theme.PicLytTheme
import com.example.piclyt.utils.ProfileCard
import com.example.piclyt.utils.createBottomNavigation
import com.example.piclyt.utils.createHeaderText
import com.google.firebase.auth.FirebaseAuth

// ########################## Ecran de paramètres ######################### //

// Fonction principale de l'écran de paramètres
@Composable
fun SettingsScreen(navController: NavController, context: Context, auth: FirebaseAuth, modifier: Modifier = Modifier) {
    createBottomNavigation(navController, context, modifier, true) // Affichage de la barre de navigation

    Column {
        createHeaderText("Settings") // Affichage du titre de la page
        ProfileCard() // Décoration temporaire
    }
}

// ########################## Preview de l'ecran de paramètres ################ //
@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    PicLytTheme {
        SettingsScreen(navController = rememberNavController(), LocalContext.current, FirebaseAuth.getInstance())
    }
}