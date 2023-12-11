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
import com.google.firebase.storage.ktx.storage

// ############################# Utilitaires pour la fonctionnalité de navigation ########################## //

// Fonction permettant de naviguer vers toutes les pages de l'application
@Composable
fun PicLytNavHost(context: ComponentActivity) {

    // Initialisation des composants de session à transférer à chaque page
    val navController = rememberNavController()
    val currentUser = authManager.getAuth.currentUser
    val db = Firebase.firestore
    val storage = Firebase.storage

    // Initialisation de la page blanche comme étant la page de départ en attendant le test
    var startDestination by remember { mutableStateOf("blank") }

    // Différents tests afin de sélectionner la page de départ
    LaunchedEffect(Unit) {
        getUsername(authManager, db) { username ->
            if (currentUser != null) { // Si connecté
                startDestination = if (username == "") { // Si username = ""
                    "Username" // Page de départ = page de choix de username
                } else {
                    "Home" // Sinon, si connecté et username != "", direction homepage
                }
            }
            else { // Si pas connecté
                startDestination = "connection" // Direction page de connexion
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