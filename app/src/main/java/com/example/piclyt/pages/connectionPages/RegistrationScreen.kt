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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.piclyt.R
import com.example.piclyt.fireBaseUtils.Registration
import com.example.piclyt.ui.theme.PicLytTheme
import com.example.piclyt.utils.CreateLogo
import com.example.piclyt.utils.CreateTextField
import com.example.piclyt.utils.createHeaderText
import com.google.firebase.auth.FirebaseAuth

// ########################## Ecran d'inscription ######################### //

// Fonction principale de la page d'inscription
@Composable
fun RegistrationScreen(navController: NavController, context: Context, auth: FirebaseAuth, modifier: Modifier = Modifier) {
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

            createHeaderText("Registration") // Affichage du titre de la page
            var emailText by rememberSaveable { mutableStateOf("") }
            var passwordText by rememberSaveable { mutableStateOf("") }
            CreateTextField(
                label = "Adresse e-mail",
                onValueChange = { emailText = it },
            ) // Champ pour saisir l'adresse email
            CreateTextField(
                label = "Mot de passe",
                onValueChange = { passwordText = it },
            ) // Champ pour saisir le mot de passe
            Button(
                onClick = { Registration(navController, context, auth, emailText, passwordText) }, // Appel de la fonction d'inscription
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Inscription")
            } // Bouton permettant de s'inscrire
        }
    }
}

// ########################## Preview de la page d'inscription ######################### //
@Preview(showBackground = true)
@Composable
fun RegistrationPreview() {
    PicLytTheme {
        RegistrationScreen(navController = rememberNavController(), LocalContext.current, FirebaseAuth.getInstance())
    }
}