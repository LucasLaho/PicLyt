package com.example.piclyt.pages.annexePages

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.piclyt.MainActivity.Companion.listMedias
import com.example.piclyt.utils.createBottomNavigation
import com.example.piclyt.utils.createHeaderText

// ########################## Ecran de modification du profil ######################### //

// Fonction principale pour la modification du profil de l'utilisateur
@Composable
fun ModifyProfileScreen(navController: NavController, context: Context, modifier: Modifier = Modifier) {
    createBottomNavigation(navController, context, modifier, true, listMedias) // Affichage de la barre de navigation
    createHeaderText("Modifier le profil") // Affichage du titre

    //ModifierProfilePage(Unit) // BUG ! TO DO pour Yohan
}