package com.example.piclyt.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.piclyt.ui.theme.PicLytTheme

// ########################## Utilitaires de l'écran de profil ######################### //
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
        // Ajoutez d'autres éléments de modification du profil selon vos besoins
    }
}

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
            ModifierProfilePage(
                onBackPressed = { navController.popBackStack() }
            )
        }
    }
}*/
