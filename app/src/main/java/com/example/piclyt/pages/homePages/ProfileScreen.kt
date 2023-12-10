package com.example.piclyt.pages.homePages

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.piclyt.MainActivity.Companion.authManager
import com.example.piclyt.MainActivity.Companion.listMedias
import com.example.piclyt.R
import com.example.piclyt.fireBaseUtils.getUsername
import com.example.piclyt.utils.UserProfile
import com.example.piclyt.utils.UserProfilePage
import com.example.piclyt.utils.createBottomNavigation
import com.google.firebase.firestore.FirebaseFirestore

// ########################## Ecran de profil ######################### //

// Fonction principale de la page de profil regroupant toutes les infos d'une page de profil quelconque
@Composable
fun ProfileScreen(navController: NavController, context: Context, db: FirebaseFirestore, modifier: Modifier = Modifier) {
    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        var username by remember { mutableStateOf("") }
        createBottomNavigation(
            navController,
            context,
            modifier,
            true,
            listMedias
        ) // Affichage de la barre de navigation
        getUsername(authManager, db) { resultUsername ->
            username = resultUsername
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp) // Ajustement de l'espace en fonction de la hauteur du BottomNavigation
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text (
                        text = "Mon profil",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(5.dp),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.ExtraBold,
                    )
                }
                UserProfilePage(
                    userProfile = UserProfile(
                        username = username,
                        albumCount = 0,
                        sharedAlbumCount = 0,
                        receivedAlbumCount = 0,
                        profileImage = R.drawable.ic_launcher_foreground
                    ),
                    navController, context, authManager
                ) // Affichage des composants de la page de profil (Photo, Bouton de déconnexion, etc...). PAS TERMINÉ !!
            }
        }
    }
}