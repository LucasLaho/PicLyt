package com.example.piclyt.pages.connectionPages

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.piclyt.R
import com.example.piclyt.fireBaseUtils.AuthManager
import com.example.piclyt.fireBaseUtils.Connection
import com.example.piclyt.utils.CreateLogo
import com.example.piclyt.utils.CreatePasswordTextField
import com.example.piclyt.utils.CreateTextField
import com.example.piclyt.utils.createGoogleSignInButton
import com.example.piclyt.utils.createHeaderText
import com.google.firebase.firestore.FirebaseFirestore

// ########################## Ecran de connexion ######################### //

// Fonction principale de la page de connexion
@Composable
fun ConnectionScreen(navController: NavController, context: Context, authManager: AuthManager, db: FirebaseFirestore, modifier: Modifier = Modifier) {
    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        Box(modifier = Modifier.padding(bottom = 70.dp), contentAlignment = Alignment.TopCenter) { // Ajustez ici le padding pour déplacer le logo vers le haut
            CreateLogo(
                painter = painterResource(id = R.mipmap.ic_launcher_foreground),
                contentDescription = "Logo"
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(30.dp)
        ) {
            Spacer(modifier = Modifier.padding(top = 30.dp))

            createHeaderText("CONNEXION") /// Affichage du titre de la page

            var emailText by rememberSaveable { mutableStateOf("") }
            var passwordText by rememberSaveable { mutableStateOf("") }
            Spacer(modifier = Modifier.padding(top = 10.dp))

            CreateTextField(
                label = "Adresse e-mail",
                onValueChange = { emailText = it },
            ) // Champ de saisie de l'adresse email

            Spacer(modifier = Modifier.padding(top = 10.dp))

            CreatePasswordTextField(
                label = "Mot de passe",
                onValueChange = { passwordText = it },
            ) // Champ de saisie du mot de passe

            Spacer(modifier = Modifier.padding(top = 10.dp))

            Button(
                onClick = { Connection(navController, context, authManager, emailText, passwordText) }, // Appel de la fonction de connexion
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Connexion")
            } // Bouton de connexion
            
            Spacer(modifier = Modifier.padding(10.dp))

            Text(text = " - OU -", fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.padding(10.dp))

            createGoogleSignInButton(context,authManager,navController) // Bouton de connexion avec Google
        }

        Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.BottomCenter) {
            Button(onClick = { navController.navigate("registration") }) {
                Text("Pas encore de compte ? Inscris-toi !")
            } // Bouton pour se rédiriger vers la page d'inscription
        }
    }
}