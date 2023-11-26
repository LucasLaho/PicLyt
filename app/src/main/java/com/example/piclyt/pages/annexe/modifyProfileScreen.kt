package com.example.piclyt.pages.annexe

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.piclyt.ui.theme.PicLytTheme
import com.example.piclyt.utils.createBottomNavigation
import com.example.piclyt.utils.createHeaderText
import com.google.firebase.auth.FirebaseAuth

// ########################## Ecran de modification du profil ######################### //

// Fonction principale pour la modification du profil de l'utilisateur
@Composable
fun ModifyProfileScreen(navController: NavController, context: Context, auth: FirebaseAuth, modifier: Modifier = Modifier) {
    createBottomNavigation(navController, context, modifier, true) // Affichage de la barre de navigation
    createHeaderText("Modifier le profil") // Affichage du titre

    //ModifierProfilePage(Unit) // BUG ! TO DO pour Yohan
}

// ########################## Preview de l'ecran de paramètres ################ //
@Preview(showBackground = true)
@Composable
fun ModifyProfileScreenPreview() {
    PicLytTheme {
        ModifyProfileScreen(navController = rememberNavController(), LocalContext.current, FirebaseAuth.getInstance())
    }
}