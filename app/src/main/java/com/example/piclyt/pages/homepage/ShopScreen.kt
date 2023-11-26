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

// ########################## Ecran de Boutique ######################### //
// Utilité : Elle sera simple au possbile et accordera des avantages à l'utilisateur définis dans le WIKI

// Fonction principale de la page de boutique
@Composable
fun ShopScreen(navController: NavController, context: Context, auth: FirebaseAuth, modifier: Modifier = Modifier) {
    createBottomNavigation(navController, context, modifier, true) // Affichage de la barre de navigation

    Column {
        createHeaderText("Shop") // Affichage du titre de la page
    }
}

// ########################## Preview de l'ecran de Boutique ################ //
@Preview(showBackground = true)
@Composable
fun ShopScreenPreview() {
    PicLytTheme {
        ShopScreen(navController = rememberNavController(), LocalContext.current, FirebaseAuth.getInstance())
    }
}