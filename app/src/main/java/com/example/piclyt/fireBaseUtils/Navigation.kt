package com.example.piclyt.fireBaseUtils

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.piclyt.pages.annexePages.ModifyProfileScreen
import com.example.piclyt.pages.homePages.AddScreen
import com.example.piclyt.pages.homePages.HomeScreen
import com.example.piclyt.pages.connectionPages.ConnectionScreen
import com.example.piclyt.pages.connectionPages.RegistrationScreen
import com.example.piclyt.pages.homePages.ProfileScreen
import com.example.piclyt.pages.annexePages.SettingsScreen
import com.example.piclyt.pages.connectionPages.UsernameScreen
import com.example.piclyt.pages.homePages.FriendsScreen
import com.example.piclyt.pages.homePages.ShopScreen
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.piclyt.MainActivity.Companion.authManager
import com.example.piclyt.MainActivity.Companion.db

// ############################# Utilitaires pour la fonctionnalité de navigation ########################## //

// Fonction permettant de naviguer vers toutes les pages de l'application
@Composable
fun PicLytNavHost(context: ComponentActivity) {

    // Initialisation des composants de session à transférer à chaque page
    val navController = rememberNavController()
    val currentUser = authManager.getAuth.currentUser
    var startDestination = "connection"

    if (currentUser != null)  // Test d'une possible connexion existante
        startDestination = "Home"

    // Liste des navigations possibles dans l'application
    NavHost(navController = navController, startDestination) {
        composable("connection") { // Vers la page de connexion
            ConnectionScreen(navController = navController, context.applicationContext, db, modifier = Modifier.fillMaxSize())
        }
        composable("registration") {// Vers la page d'inscription
            RegistrationScreen(navController = navController, context.applicationContext, modifier = Modifier.fillMaxSize())
        }
        composable("Username") {// Vers la page de choix de nom d'utilisateur
            UsernameScreen(navController = navController, context.applicationContext, db, modifier = Modifier.fillMaxSize())
        }
        composable("Home") {// Vers la page d'accueil/liste des albums
            HomeScreen(navController = navController, context.applicationContext, db, modifier = Modifier.fillMaxSize())
        }
        composable("Profile") {// Vers la page de profil
            ProfileScreen(navController = navController, context.applicationContext, modifier = Modifier.fillMaxSize())
        }
        composable("ModifyProfile") {// Vers la page de profil
            ModifyProfileScreen(navController = navController, context.applicationContext, modifier = Modifier.fillMaxSize())
        }
        composable("Friends") {// Vers la page d'Amis
            FriendsScreen(navController = navController, context.applicationContext, modifier = Modifier.fillMaxSize())
        }
        composable("Add") {// Vers la page d'ajout d'album
            AddScreen(navController = navController, context.applicationContext, modifier = Modifier.fillMaxSize())
        }
        composable("Settings") {// Vers la page de paramètres
            SettingsScreen(navController = navController, context.applicationContext, modifier = Modifier.fillMaxSize())
        }
        composable("Shop") {// Vers la page de boutique
            ShopScreen(navController = navController, context.applicationContext, modifier = Modifier.fillMaxSize())
        }
    }
}

