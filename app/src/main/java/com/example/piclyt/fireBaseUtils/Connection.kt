package com.example.piclyt.fireBaseUtils

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.example.piclyt.MainActivity.Companion.authManager
import com.example.piclyt.utils.testDataFields
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

// ############################# Utilitaires pour la fonctionnalité de connexion ########################## //

// Fonction permettant d'établir une connexion à Firebase depuis l'écran de connexion
fun Connection(navController: NavController, context: Context, emailText: String, passwordText: String) {

    if (testDataFields(context, emailText, passwordText)) {
        authManager.getAuth.signInWithEmailAndPassword(emailText, passwordText)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) { // Cas 1 : Connexion réussie
                    Toast.makeText(context, "Bienvenue !", Toast.LENGTH_SHORT).show()
                    navController.navigate("Home") {
                        popUpTo("connection") { inclusive = true } // Suppression de l'historique de navigation
                    }
                } else {
                    // En cas d'echec de la connexion
                    val exception = task.exception
                    when {
                        exception is FirebaseAuthInvalidUserException -> {
                            // Si l'utilisateur n'existe pas
                            Toast.makeText(context, "Le compte n'existe pas !", Toast.LENGTH_SHORT).show() // S'AFFICHE PAS
                        }
                        exception is FirebaseAuthInvalidCredentialsException -> {
                            // Si le mot de passe est incorrect
                            Toast.makeText(context, "Mot de passe incorrect !", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            // Toute autre erreur
                            Toast.makeText(context, "Une erreur est survenue. Veuillez réessayer !", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
    }
}