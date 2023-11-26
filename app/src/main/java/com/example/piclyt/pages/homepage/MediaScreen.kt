package com.example.piclyt.pages.homepage

import android.content.Context
import androidx.compose.foundation.layout.Column
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

// ########################## Ecran de Média ######################### //

// Fonction principale de l'écran de Média où on retrouvera les photos et vidéos de l'utilisateur depuis sa galerie
@Composable
fun MediaScreen(navController: NavController, context: Context, auth: FirebaseAuth, modifier: Modifier = Modifier) {
    createBottomNavigation(navController, context, modifier, true) // Affichage de la barre de navigation

    Column {
        createHeaderText("Média") // Affichage du titre
    }
}

// ########################## Preview de l'ecran de Média ################ //
@Preview(showBackground = true)
@Composable
fun MediaScreenPreview() {
    PicLytTheme {
        MediaScreen(navController = rememberNavController(), LocalContext.current, FirebaseAuth.getInstance())
    }
}