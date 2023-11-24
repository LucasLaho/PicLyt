package com.example.piclyt.fireBaseUtils

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.piclyt.utils.testDataFields
import com.google.firebase.auth.FirebaseAuth

// ############################# Utilitaires pour la fonctionnalité de connexion ########################## //
fun Connection(navController: NavController, context: Context, auth: FirebaseAuth, emailText: String, passwordText: String) {

    if(testDataFields(context, emailText, passwordText)){
        // Exécution de la connexion à Firebase
        auth.signInWithEmailAndPassword(emailText, passwordText)
            .addOnCompleteListener() {

                    task -> if (task.isSuccessful) { // En cas de réussite, redirection vers la page d'accueil
                navController.navigate("Home") {
                    popUpTo("connection") { inclusive = true }
                }
            }

            else { // En cas d'erreur
                Toast.makeText(context, "Une erreur est survenue, veuillez réessayer", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

