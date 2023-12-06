package com.example.piclyt.fireBaseUtils

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.piclyt.data.MediaModel
import com.example.piclyt.pages.annexePages.ModifyProfileScreen
import com.example.piclyt.pages.homePages.AddScreen
import com.example.piclyt.pages.homePages.HomeScreen
import com.example.piclyt.pages.homePages.MediaScreen
import com.example.piclyt.pages.connectionPages.ConnectionScreen
import com.example.piclyt.pages.connectionPages.RegistrationScreen
import com.example.piclyt.pages.homePages.ProfileScreen
import com.example.piclyt.pages.annexePages.SettingsScreen
import com.example.piclyt.pages.homePages.ShopScreen

// ############################# Utilitaires pour la fonctionnalité de navigation ########################## //

// Fonction permettant de naviguer vers toutes les pages de l'application
@Composable
fun PicLytNavHost(context: ComponentActivity) {

    // Initialisation des composants de session à transférer à chaque page
    var authManager = AuthManager(context)
    val navController = rememberNavController()
    val currentUser = authManager.getAuth.currentUser
    var startDestination = "connection"
    val listMedias = MediaModel.getInstance() // Liste des images enregistrées par l'utilisateur tranférées à toutes les pages via la navigation

    if (currentUser != null) { // Test d'une possible connexion en suspens
        startDestination = "Home"
    }

    // Liste des navigations possibles dans l'application
    NavHost(navController = navController, startDestination) {
        composable("connection") { // Vers la page de connexion
            ConnectionScreen(navController = navController, context.applicationContext, authManager, modifier = Modifier.fillMaxSize())
        }
        composable("registration") {// Vers la page d'inscription
            RegistrationScreen(navController = navController, context.applicationContext, authManager, modifier = Modifier.fillMaxSize())
        }
        composable("Home") {// Vers la page d'accueil/liste des albums
            HomeScreen(navController = navController, context.applicationContext, authManager, modifier = Modifier.fillMaxSize(), listMedias)
        }
        composable("Profile") {// Vers la page de profil
            ProfileScreen(navController = navController, context.applicationContext, authManager, modifier = Modifier.fillMaxSize(), listMedias)
        }
        composable("ModifyProfile") {// Vers la page de profil
            ModifyProfileScreen(navController = navController, context.applicationContext, authManager, modifier = Modifier.fillMaxSize(), listMedias)
        }
        composable("Media") {// Vers la page de Média
            MediaScreen(navController = navController, context.applicationContext, authManager, modifier = Modifier.fillMaxSize(), listMedias)
        }
        composable("Add") {// Vers la page d'ajout d'album
            AddScreen(navController = navController, context.applicationContext, authManager, modifier = Modifier.fillMaxSize(), listMedias)
        }
        composable("Settings") {// Vers la page de paramètres
            SettingsScreen(navController = navController, context.applicationContext, authManager, modifier = Modifier.fillMaxSize(), listMedias)
        }
        composable("Shop") {// Vers la page de boutique
            ShopScreen(navController = navController, context.applicationContext, authManager, modifier = Modifier.fillMaxSize(), listMedias)
        }
    }
}

