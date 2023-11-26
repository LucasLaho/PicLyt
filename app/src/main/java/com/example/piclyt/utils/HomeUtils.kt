package com.example.piclyt.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.piclyt.R
import com.google.firebase.auth.FirebaseAuth

// ############################# Liste des fonctions de la page d'accueil ########################## //

// Fonction pour afficher les albums photo
@Composable
fun AlbumSection(navController: NavController, auth: FirebaseAuth) {
    val context = LocalContext.current

    // Récupérer la liste des albums depuis Firebase (Ne le fais pas actuellement. A DEV !!!)
    val albums = remember {
        listOf(
            Album("Vacances", R.drawable.ic_launcher_background),
            Album("Famille", R.drawable.ic_launcher_background),
            Album("Mariage", R.drawable.ic_launcher_background)
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(albums) { album ->
            AlbumItem(album = album, onClick = {
                // Naviguer vers l'écran de détails de l'album
                navController.navigate("album/${album.name}")
            })
        }
    }
}

// Composant pour représenter un album
@Composable
fun AlbumItem(album: Album, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        // Image de l'album
        Image(
            painter = painterResource(id = album.imageResource),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(CutCornerShape(CornerSize(16.dp)))
                .clickable(onClick = onClick)
                .background(MaterialTheme.colorScheme.surface)
                .shadow(4.dp)
        )

        // Titre de l'album
        Text(
            text = album.name,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )
    }
}

// Modèle de données pour un album
data class Album(val name: String, val imageResource: Int)

// Fonction pour afficher un message de bienvenu
@Composable
fun GreetingSection(
    name: String // Represente le nom de l'utilisateur connecté
) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Bonjour, $name",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Quelle belle journée aujourd'hui !",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
