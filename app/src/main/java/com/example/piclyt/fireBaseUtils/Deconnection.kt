package com.example.piclyt.fireBaseUtils

import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

// ############################# Utilitaires pour la fonctionnalité de déconnexion ########################## //

// Fonction permettant d'établir une déconnexion à Firebase depuis l'écran de profil
fun Deconnection(navController: NavController) {
    Firebase.auth.signOut() // Fonction de Déconnexion de Firebase
    navController.navigate("connection") { // Redirection vers la page de connexion
        popUpTo("Home") { inclusive = true }
    }
}