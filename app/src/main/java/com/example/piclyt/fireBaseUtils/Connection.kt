package com.example.piclyt.fireBaseUtils

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.example.piclyt.utils.testDataFields
import com.google.firebase.auth.FirebaseAuth

// ############################# Utilitaires pour la fonctionnalité de connexion ########################## //

// Fonction permettant d'établir une connexion à Firebase depuis l'écran de connexion
fun Connection(navController: NavController, context: Context, auth: FirebaseAuth, emailText: String, passwordText: String) {

    if(testDataFields(context, emailText, passwordText)){
        // Exécution de la connexion à Firebase
        auth.signInWithEmailAndPassword(emailText, passwordText)
            .addOnCompleteListener() {

                    task -> if (task.isSuccessful) { // En cas de réussite, redirection vers la page d'accueil
                navController.navigate("Home") {
                    popUpTo("connection") { inclusive = true } // Suppression de l'historique de navigation
                }
            }

            else { // En cas d'erreur, on affiche une pop-up
                Toast.makeText(context, "Échec de la connexion ! Veuillez réessayer", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

