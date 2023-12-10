package com.example.piclyt.fireBaseUtils

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.piclyt.pages.annexePages.SetProfileScreen
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
import com.example.piclyt.pages.annexePages.AlbumScreen
import com.example.piclyt.pages.annexePages.BlankScreen

// ############################# Utilitaires pour la fonctionnalité de navigation ########################## //

// Fonction permettant de naviguer vers toutes les pages de l'application
@Composable
fun PicLytNavHost(context: ComponentActivity) {

    // Initialisation des composants de session à transférer à chaque page
    val navController = rememberNavController()
    val currentUser = authManager.getAuth.currentUser
    val db = Firebase.firestore

    /*// Test d'une possible connexion existante
    getUsername(authManager, db) {username ->
        if (currentUser != null) {
            if(username=="") {
                startDestination = "Username" // Si username non défini, redirection vers usernamescreen
            }
            else startDestination = "Home" // Sinon redirection vers homepage
        }
    }*/

    // State to store the start destination
    var startDestination by remember { mutableStateOf("blank") }

    // Asynchronous side effect to check for a possible existing connection
    LaunchedEffect(Unit) {
        getUsername(authManager, db) { username ->
            if (currentUser != null) {
                startDestination = if (username == "") {
                    "Username"
                } else {
                    "Home"
                }
            }
            else {
                startDestination = "connection"
            }
        }
    }

    // Liste des navigations possibles dans l'application
    NavHost(navController = navController, startDestination) {

        // ############# Connection / Inscription pages #################### //
        composable("connection") { // Vers la page de connexion
            ConnectionScreen(navController = navController, context.applicationContext, db, modifier = Modifier.fillMaxSize())
        }
        composable("registration") {// Vers la page d'inscription
            RegistrationScreen(navController = navController, context.applicationContext, modifier = Modifier.fillMaxSize())
        }
        composable("Username") {// Vers la page de choix de nom d'utilisateur
            UsernameScreen(navController = navController, context.applicationContext, db, modifier = Modifier.fillMaxSize())
        }

        // ############## Main pages after connection ####################### //
        composable("Home") {// Vers la page d'accueil/liste des albums
            HomeScreen(navController = navController, context.applicationContext, db, modifier = Modifier.fillMaxSize())
        }
        composable("Profile") {// Vers la page de profil
            ProfileScreen(navController = navController, context.applicationContext, db, modifier = Modifier.fillMaxSize())
        }
        composable("Friends") {// Vers la page d'Amis
            FriendsScreen(navController = navController, context.applicationContext, db, modifier = Modifier.fillMaxSize())
        }
        composable("Add") {// Vers la page d'ajout d'album
            AddScreen(navController = navController, context.applicationContext, modifier = Modifier.fillMaxSize())
        }
        composable("Shop") {// Vers la page de boutique
            ShopScreen(navController = navController, context.applicationContext, modifier = Modifier.fillMaxSize())
        }

        // ############## Annexe pages from main pages ####################### //
        composable("Settings") {// Vers la page de paramètres
            SettingsScreen(navController = navController, context.applicationContext, modifier = Modifier.fillMaxSize())
        }
        composable("ModifyProfile") {// Vers la page de profil
            SetProfileScreen(navController = navController, context.applicationContext, modifier = Modifier.fillMaxSize())
        }
        composable("Album") {// Vers la page d'album
            AlbumScreen(navController = navController, context.applicationContext, db, modifier = Modifier.fillMaxSize())
        }
        composable("blank") { // Vers la page de connexion
            BlankScreen(modifier = Modifier.fillMaxSize())
        }
    }
}