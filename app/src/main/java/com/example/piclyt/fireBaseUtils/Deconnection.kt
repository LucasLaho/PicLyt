package com.example.piclyt.fireBaseUtils

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

// ############################# Utilitaires pour la fonctionnalité de déconnexion ########################## //
fun Deconnection(navController: NavController, context: Context, auth: FirebaseAuth) {
    Firebase.auth.signOut() // Déconnexion de Firebase
    navController.navigate("connection") { // Redirection vers la page de connexion
        popUpTo("homepage") { inclusive = true }
    }
}