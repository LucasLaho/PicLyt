package com.example.piclyt.fireBaseUtils

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.piclyt.pages.homepage.AddScreen
import com.example.piclyt.pages.homepage.HomeScreen
import com.example.piclyt.pages.homepage.MediaScreen
import com.example.piclyt.pages.registration_connection.ConnectionScreen
import com.example.piclyt.pages.registration_connection.RegistrationScreen
import com.example.piclyt.pages.homepage.ProfileScreen
import com.example.piclyt.pages.annexe.SettingsScreen
import com.example.piclyt.pages.homepage.ShopScreen
import com.google.firebase.auth.FirebaseAuth

// ############################# Utilitaires pour la fonctionnalité de navigation ########################## //
@Composable
fun PicLytNavHost(context: Context, auth: FirebaseAuth) {

    // Initialisation des composants de session à transférer à chaque page
    val navController = rememberNavController()
    val currentUser = auth.currentUser
    var startDestination = "connection"

    if (currentUser != null) { // Test d'une possible connexion en suspens
        startDestination = "Home"
    }

    // Liste des navigations possibles dans l'application
    NavHost(navController = navController, startDestination) {
        composable("connection") { // Vers la page de connexion
            ConnectionScreen(navController = navController, context, auth, modifier = Modifier.fillMaxSize())
        }
        composable("registration") {// Vers la page d'inscription
            RegistrationScreen(navController = navController, context, auth, modifier = Modifier.fillMaxSize())
        }
        composable("Home") {// Vers la page d'accueil
            HomeScreen(navController = navController, context, auth, modifier = Modifier.fillMaxSize())
        }
        composable("Profile") {// Vers la page de profil
            ProfileScreen(navController = navController, context, auth, modifier = Modifier.fillMaxSize())
        }
        composable("Media") {// Vers la page de Média
            MediaScreen(navController = navController, context, auth, modifier = Modifier.fillMaxSize())
        }
        composable("Add") {// Vers la page d'ajout d'album
            AddScreen(navController = navController, context, auth, modifier = Modifier.fillMaxSize())
        }
        composable("Settings") {// Vers la page de paramètres
            SettingsScreen(navController = navController, context, auth, modifier = Modifier.fillMaxSize())
        }
        composable("Shop") {// Vers la page de boutique
            ShopScreen(navController = navController, context, auth, modifier = Modifier.fillMaxSize())
        }
    }
}