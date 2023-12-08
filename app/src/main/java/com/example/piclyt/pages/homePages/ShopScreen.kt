package com.example.piclyt.pages.homePages

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.piclyt.MainActivity.Companion.listMedias
import com.example.piclyt.utils.createBottomNavigation
import com.example.piclyt.utils.createHeaderText

// ########################## Ecran de Boutique ######################### //
// Utilité : Elle sera simple au possbile et accordera des avantages à l'utilisateur définis dans le WIKI

// Fonction principale de la page de boutique
@Composable
fun ShopScreen(navController: NavController, context: Context, modifier: Modifier = Modifier) {
    createBottomNavigation(navController, context, modifier, true, listMedias) // Affichage de la barre de navigation

    Column {
        createHeaderText("Shop") // Affichage du titre de la page
    }
}