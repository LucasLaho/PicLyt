package com.example.piclyt.pages.homepage

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.piclyt.R
import com.example.piclyt.fireBaseUtils.Deconnection
import com.example.piclyt.utils.createBottomNavigation
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

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

data class UserProfile(
    val username: String,
    val albumCount: Int,
    val sharedAlbumCount: Int,
    val receivedAlbumCount: Int,
    val profileImage: Int // Replace with your actual image resource or URL
)

@Composable
fun UserProfilePage(userProfile: UserProfile, navController: NavController, context: Context, auth: FirebaseAuth ) {
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
            EditProfileButton(onClick = { isEditing = true })
        }

        item {
            navigateToSettings(navController, onClick = { isEditing = true })
        }

        item {
            DeconnexionButton(navController, context, auth, onClick = { isEditing = true })
        }
    }
}

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

@Composable
fun EditProfileButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text("Éditer le profil")
    }
}

@Composable
fun DeconnexionButton(navController: NavController, context: Context, auth: FirebaseAuth, onClick: () -> Unit) {
    Button(
        onClick = { Deconnection(navController, context, auth) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text("Déconnexion")
    }
}

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