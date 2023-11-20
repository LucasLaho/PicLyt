package com.example.piclyt

// EditProfileButton.kt
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
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
            ModifierProfilePage(
                onBackPressed = { navController.popBackStack() }
            )
        }
    }
}
