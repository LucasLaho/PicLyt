package com.example.piclyt

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.piclyt.connection.ConnectionScreen
import com.example.piclyt.connection.RegistrationScreen
import com.example.piclyt.homepage.HomePageScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun PicLytNavHost(context: Context, auth: FirebaseAuth) {

    val navController = rememberNavController()
    val currentUser = auth.currentUser
    var startDestination = "connection"

    if (currentUser != null) {
        startDestination = "homepage"
    }

    NavHost(navController = navController, startDestination) {
        composable("connection") {
            ConnectionScreen(navController = navController, context, auth, modifier = Modifier.fillMaxSize())
        }
        composable("registration") {
            RegistrationScreen(navController = navController, context, auth, modifier = Modifier.fillMaxSize())
        }
        composable("homepage") {
            HomePageScreen(navController = navController, context, auth, modifier = Modifier.fillMaxSize())
        }
    }
}