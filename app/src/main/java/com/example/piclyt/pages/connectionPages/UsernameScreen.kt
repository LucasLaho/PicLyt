package com.example.piclyt.pages.connectionPages

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.piclyt.R
import com.example.piclyt.fireBaseUtils.AuthManager
import com.example.piclyt.fireBaseUtils.Username
import com.example.piclyt.utils.CreateLogo
import com.example.piclyt.utils.CreateTextField
import com.example.piclyt.utils.createHeaderText
import com.google.firebase.firestore.FirebaseFirestore

// ########################## Ecran de choix de nom d'utilisateur ######################### //

// Fonction principale de la page d'inscription
@Composable
fun UsernameScreen(
    navController: NavController,
    context: Context,
    authManager: AuthManager,
    db: FirebaseFirestore,
    modifier: Modifier = Modifier
) {

    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.TopCenter) {
            CreateLogo(
                painter = painterResource(id = R.mipmap.ic_launcher_foreground),
                contentDescription = "Logo"
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {

            createHeaderText("Choix du nom d'utilisateur") // Affichage du titre de la page
            var usernameText by rememberSaveable { mutableStateOf("") }
            CreateTextField(
                label = "Nom d'utilisateur",
                onValueChange = { usernameText = it },
            ) // Champ pour saisir le nom d'utilisateur
            Button(
                onClick = { Username(navController, context, authManager, db, usernameText) }, // Appel de la fonction de sélection du nom d'utilisateur
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Valider")
            } // Bouton permettant de valider le nom d'utilisateur
        }
    }
}