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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.piclyt.R
import com.example.piclyt.fireBaseUtils.Registration
import com.example.piclyt.utils.CreateLogo
import com.example.piclyt.utils.CreatePasswordTextField
import com.example.piclyt.utils.CreateTextField
import com.example.piclyt.utils.createHeaderText

// ########################## Ecran d'inscription ######################### //

// Fonction principale de la page d'inscription
@Composable
fun RegistrationScreen(navController: NavController, context: Context, modifier: Modifier = Modifier) {

    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        Box(modifier = Modifier.padding(bottom = 550.dp), contentAlignment = Alignment.TopCenter) {
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

            Spacer(modifier = Modifier.padding(10.dp))

            createHeaderText("INSCRIPTION") // Affichage du titre de la page

            var emailText by rememberSaveable { mutableStateOf("") }
            var passwordText by rememberSaveable { mutableStateOf("") }

            Spacer(modifier = Modifier.padding(top = 10.dp))

            Text(text = "Veuillez saisir une adresse mail valide",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(top = 10.dp))

            CreateTextField(
                label = "Adresse e-mail",
                onValueChange = { emailText = it },
            ) // Champ pour saisir l'adresse email

            Spacer(modifier = Modifier.padding(20.dp))

            Text(text = "Veuillez saisir un mot de passe\n(>= 6 caractères)",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(top = 10.dp))

            CreatePasswordTextField(
                label = "Mot de passe",
                onValueChange = { passwordText = it },
            ) // Champ pour saisir le mot de passe

            Spacer(modifier = Modifier.padding(top = 20.dp))

            Button(
                onClick = { Registration(navController, context, emailText, passwordText) }, // Appel de la fonction d'inscription
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Suivant")
            } // Bouton permettant de s'inscrire

            Button(
                onClick = { navController.navigate("connection") }, // Retour à la page d'inscription
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Retour")
            } // Bouton permettant de retourner en arrière
        }
    }
}
