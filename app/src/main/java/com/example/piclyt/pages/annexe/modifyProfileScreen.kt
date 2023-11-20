package com.example.piclyt.pages.annexe

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.piclyt.utils.createBottomNavigation
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ModifyProfileScreen(navController: NavController, context: Context, auth: FirebaseAuth, modifier: Modifier = Modifier) {
    createBottomNavigation(navController, context, modifier, true) // Affichage de la navigation

    //ModifierProfilePage(Unit)
}
