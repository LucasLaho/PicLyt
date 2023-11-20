package com.example.piclyt.fireBaseUtils

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.example.piclyt.utils.testDataFields
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

// ############################# Utilitaires pour la fonctionnalité d'inscription ########################## //
fun Registration(navController: NavController, context: Context, auth: FirebaseAuth, emailText: String, passwordText: String) {

    if(testDataFields(context, emailText, passwordText)){
        // Exécution de la connexion à Firebase
        auth.createUserWithEmailAndPassword(emailText, passwordText)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) { // En cas de réussite, redirection vers la page d'accueil
                    Toast.makeText(context, "Compte créé avec succès", Toast.LENGTH_SHORT).show()
                    Firebase.auth.signOut()
                    navController.popBackStack()
                }

                else { // En cas d'erreur
                    Toast.makeText(context, "Une erreur est survenue, veuillez réessayer", Toast.LENGTH_SHORT).show()
                }
            }
    }
}