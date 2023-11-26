package com.example.piclyt.pages.homepage

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.piclyt.R
import com.example.piclyt.ui.theme.PicLytTheme
import com.example.piclyt.utils.UserProfile
import com.example.piclyt.utils.UserProfilePage
import com.example.piclyt.utils.createBottomNavigation
import com.google.firebase.auth.FirebaseAuth

// ########################## Ecran de profil ######################### //

// Fonction principale de la page de profil regroupant toutes les infos d'une page de profil quelconque
@Composable
fun ProfileScreen(navController: NavController, context: Context, auth: FirebaseAuth, modifier: Modifier = Modifier) {
    createBottomNavigation(navController, context, modifier, true) // Affichage de la barre de navigation

    UserProfilePage(
        userProfile = UserProfile(
            username = auth.currentUser?.email.toString(),
            albumCount = 3,
            sharedAlbumCount = 5,
            receivedAlbumCount = 2,
            profileImage = R.drawable.ic_launcher_foreground
        ),
        navController, context, auth
    ) // Affichage des composants de la page de profil (Photo, Bouton de déconnexion, etc...). PAS TERMINÉ !!
}

// ########################## Preview de l'ecran de profil ################ //
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    PicLytTheme {
        ProfileScreen(navController = rememberNavController(), LocalContext.current, FirebaseAuth.getInstance())
    }
}