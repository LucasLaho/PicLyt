package com.example.piclyt.pages.homepage

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.piclyt.ui.theme.PicLytTheme
import com.example.piclyt.utils.AlbumSection
import com.example.piclyt.utils.GreetingSection
import com.example.piclyt.utils.createBottomNavigation
import com.example.piclyt.utils.createHeaderText
import com.google.firebase.auth.FirebaseAuth

// ########################## Ecran d'accueil ######################### //

// Fonction principale de l'écran d'accueil
@Composable
fun HomeScreen(navController: NavController, context: Context, auth: FirebaseAuth, modifier: Modifier = Modifier) {
    createBottomNavigation(navController, context, modifier, true) // Affichage de la barre de navigation
    // AlbumSection(navController, auth) // Affichage des albums sur la page d'accueil. Bug encore
    createHeaderText("Albums") // Affichage du titre
    GreetingSection("Thierry") // Affichage du message de bienvenu
}

// ########################## Preview de l'ecran d'accueil ################ //
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PicLytTheme {
        HomeScreen(navController = rememberNavController(), LocalContext.current, FirebaseAuth.getInstance())
    }
}