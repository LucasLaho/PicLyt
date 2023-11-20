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
import com.example.piclyt.utils.ShopHeaderText
import com.example.piclyt.utils.createBottomNavigation
import com.google.firebase.auth.FirebaseAuth

// ########################## Ecran de Boutique ######################### //
@Composable
fun ShopScreen(navController: NavController, context: Context, auth: FirebaseAuth, modifier: Modifier = Modifier) {
    createBottomNavigation(navController, context, modifier, true) // Affichage de la navigation

    Column {
        ShopHeaderText()
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