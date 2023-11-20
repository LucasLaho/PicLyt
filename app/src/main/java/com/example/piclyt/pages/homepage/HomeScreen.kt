package com.example.piclyt.pages.homepage

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.piclyt.ui.theme.PicLytTheme
import com.example.piclyt.utils.GreetingSection
import com.example.piclyt.utils.HomeHeaderText
import com.example.piclyt.utils.createBottomNavigation
import com.google.firebase.auth.FirebaseAuth

// ########################## Ecran d'accueil ######################### //
@Composable
fun HomeScreen(navController: NavController, context: Context, auth: FirebaseAuth, modifier: Modifier = Modifier) {
    createBottomNavigation(navController, context, modifier, true) // Affichage de la navigation
    // AlbumSection(navController, auth) Bug

    HomeHeaderText()
    GreetingSection()
}

// ########################## Preview de l'ecran d'accueil ################ //
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PicLytTheme {
        HomeScreen(navController = rememberNavController(), LocalContext.current, FirebaseAuth.getInstance())
    }
}