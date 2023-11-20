package com.example.piclyt.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.piclyt.ui.theme.PicLytTheme

// ########################## Utilitaires de l'écran de profil ######################### //
/*@Composable
fun EditProfileButton() {
    val navController = rememberNavController()

    Button(
        onClick = {
            navController.navigate("modifierProfilePage") {
                // Permet de revenir à l'écran précédent lors du retour
                popUpTo("profilePage") { inclusive = true }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Éditer le profil")
    }

    // Écran de modification de profil
    NavHost(navController, startDestination = "profilePage") {
        composable("profilePage") {
            // Votre écran de profil existant
            // Utilisez UserProfilePage(userProfile) ici
        }
        composable("modifierProfilePage") {
            com.example.piclyt.utils.ModifierProfilePage(
                onBackPressed = { navController.popBackStack() }
            )
        }
    }
}*/
