package com.example.piclyt.pages.homepage

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.piclyt.R
import com.example.piclyt.utils.UserProfile
import com.example.piclyt.utils.UserProfilePage
import com.example.piclyt.utils.createBottomNavigation
import com.google.firebase.auth.FirebaseAuth

// ########################## Ecran de profil ######################### //
@Composable
fun ProfileScreen(navController: NavController, context: Context, auth: FirebaseAuth, modifier: Modifier = Modifier) {
    createBottomNavigation(navController, context, modifier, true) // Affichage de la navigation

    UserProfilePage(
        userProfile = UserProfile(
            username = auth.currentUser?.email.toString(),
            albumCount = 3,
            sharedAlbumCount = 5,
            receivedAlbumCount = 2,
            profileImage = R.drawable.ic_launcher_foreground
        ),
        navController, context, auth
    )
}

