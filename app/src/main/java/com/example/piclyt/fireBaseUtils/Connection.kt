package com.example.piclyt.fireBaseUtils

import android.content.Context
import android.widget.Toast
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