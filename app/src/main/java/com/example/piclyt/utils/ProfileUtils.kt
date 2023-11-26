package com.example.piclyt.utils

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.piclyt.fireBaseUtils.Deconnection
import com.google.firebase.auth.FirebaseAuth

// ########################## Utilitaires de l'écran de profil ######################### //
data class UserProfile(
    val username: String,
    val albumCount: Int,
    val sharedAlbumCount: Int,
    val receivedAlbumCount: Int,
    val profileImage: Int // Replace with your actual image resource or URL
)

// Fonction affichant tous les composants sur l'écran de profil ! Composants crées par les fonctions plus bas
@Composable
fun UserProfilePage(userProfile: UserProfile, navController: NavController, context: Context, auth: FirebaseAuth) {
    var isEditing by remember { mutableStateOf(false) }

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                ProfilImage(
                    painter = painterResource(id = userProfile.profileImage),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(200.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = userProfile.username,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            ProfileStats(userProfile = userProfile)
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            EditProfileButton(navController, onClick = { isEditing = true })
        }

        item {
            navigateToSettings(navController, onClick = { isEditing = true })
        }

        item {
            DeconnexionButton(navController, onClick = { isEditing = true })
        }
    }
}

// Fonction affichant la photo de profil de l'utilisateur. (NE LE FAIS PAS ENCORE)
@Composable
fun ProfilImage(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
            .size(150.dp)  // Ici on peut augmenter ou diminuer la taille de l'image !
    )
}

// Fonction pour afficher les statistiques de l'utilisateur
@Composable
fun ProfileStats(userProfile: UserProfile) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.Gray)
            .padding(8.dp)
    ) {
        ProfileStatItem(label = "Nombre d'albums", value = userProfile.albumCount)
        ProfileStatItem(label = "Albums partagés", value = userProfile.sharedAlbumCount)
        ProfileStatItem(label = "Albums reçus", value = userProfile.receivedAlbumCount)
    }
}

// Fonction pour gérer la police d'écriture de la page de profil
@Composable
fun ProfileStatItem(label: String, value: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium, // Utilisez le style par défaut
            modifier = Modifier.weight(1f)
        )

        Text(
            text = value.toString(),
            style = LocalTextStyle.current
        )
    }
}

// Fonction de création d'un bouton pour se rédiriger la page de modification de profil
@Composable
fun EditProfileButton(navController: NavController, onClick: () -> Unit) {
    Button(
        onClick = { navController.navigate("ModifyProfile") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text("Éditer le profil")
    }
}

// Fonction pour afficher le bouton de déconnexion pour quitter l'application
@Composable
fun DeconnexionButton(navController: NavController, onClick: () -> Unit) {
    Button(
        onClick = { Deconnection(navController) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text("Déconnexion")
    }
}

// Fonction pour afficher le bouton de navigation vers la page de paramètres
@Composable
fun navigateToSettings(navController: NavController, onClick: () -> Unit) {
    Button(
        onClick = { navController.navigate("Settings") { // Redirection vers la page de paramétres
        }},
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text("Paramètres")
    }
}

// ########################## Utilitaires de l'écran de modification de profil ######################### //
// Utilité : Cette page servira à l'utilisateur de modification de sa photo de profil, etc...

// Fonction pour modifier le profil de l'utilisateur. PAS UTILISÉ ENCORE. TO DO pour Yohan
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModifierProfilePage(onBackPressed: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        TopAppBar(
            title = { Text("Modifier le profil") },
            navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ModifierProfileItem(label = "Adresse e-mail", value = "utilisateur@email.com")
        ModifierProfileItem(label = "Nom d'utilisateur", value = "NomUtilisateur")
    }
}

// ?????? TO DO pour Yohan
@Composable
fun ModifierProfileItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = label, style = MaterialTheme.typography.bodyLarge)
            Text(text = value, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

////////////////////// Fonctions buggés. TO DO pour YOHAN /////////////////////////////////
/*@Composable
fun EditProfileButton() {
    val navController = rememberNavController()

    Button(
        onClick = {
            navController.navigate("modifierProfilePage") {
                // Permet de revenir à l'écran précédent lors du retour
                popUpTo("profilePage") { inclusive = true }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Éditer le profil")
    }

    // Écran de modification de profil
    NavHost(navController, startDestination = "profilePage") {
        composable("profilePage") {
            // Votre écran de profil existant
            // Utilisez UserProfilePage(userProfile) ici
        }
        composable("modifierProfilePage") {
            com.example.piclyt.utils.ModifierProfilePage(
                onBackPressed = { navController.popBackStack() }
            )
        }
    }
}*/