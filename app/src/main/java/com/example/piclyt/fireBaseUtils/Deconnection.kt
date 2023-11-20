package com.example.piclyt.fireBaseUtils

import android.content.Context
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

// ############################# Utilitaires pour la fonctionnalité de déconnexion ########################## //
fun Deconnection(navController: NavController, context: Context, auth: FirebaseAuth) {
    Firebase.auth.signOut() // Déconnexion de Firebase
    navController.navigate("connection") { // Redirection vers la page de connexion
        popUpTo("Home") { inclusive = true }
    }
}