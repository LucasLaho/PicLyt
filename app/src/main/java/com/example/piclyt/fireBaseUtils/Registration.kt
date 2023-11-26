package com.example.piclyt.fireBaseUtils

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.example.piclyt.utils.testDataFields
import com.google.firebase.auth.FirebaseAuth

// ############################# Utilitaires pour la fonctionnalité d'inscription ########################## //

// Fonction permettant d'établir une inscription à Firebase depuis l'écran d'inscription
fun Registration(navController: NavController, context: Context, auth: FirebaseAuth, emailText: String, passwordText: String) {

    if(testDataFields(context, emailText, passwordText)){
        // Exécution de l'ajout d'un compte à Firebase
        auth.createUserWithEmailAndPassword(emailText, passwordText)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) { // En cas de réussite, redirection directement vers la page d'accueil
                    Toast.makeText(context, "Compte créé avec succès", Toast.LENGTH_SHORT).show()
                    navController.navigate("Home") {
                        popUpTo("connection") { inclusive = true }
                    }
                }

                else { // En cas d'erreur, affichage d'une pop-up
                    Toast.makeText(context, "Echec de l'inscription ! Veuillez réessayer", Toast.LENGTH_SHORT).show()
                }
        }
    }
}