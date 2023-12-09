package com.example.piclyt.fireBaseUtils

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.example.piclyt.MainActivity.Companion.authManager
import com.example.piclyt.utils.testDataFields
import com.google.firebase.auth.FirebaseAuth

// ############################# Utilitaires pour la fonctionnalité d'inscription ########################## //

// Fonction permettant d'établir une inscription à Firebase depuis l'écran d'inscription
fun Registration(navController: NavController, context: Context, emailText: String, passwordText: String) {

   /* if(testDataFields(context, emailText, passwordText)){
        Toast.makeText(context, "Informations correctes", Toast.LENGTH_SHORT).show()
        navController.navigate("Username") {
            popUpTo("connection") { inclusive = true }
        }
        authManager.initLogin(emailText,passwordText)
    }*/

    if(testDataFields(context, emailText, passwordText)){
        // Exécution de l'ajout d'un compte à Firebase
        authManager.getAuth.createUserWithEmailAndPassword(emailText, passwordText)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) { // En cas de réussite, redirection directement vers la page d'accueil
                    Toast.makeText(context, "Compte créé avec succès", Toast.LENGTH_SHORT).show()
                    navController.navigate("Username") {
                        popUpTo("connection") { inclusive = true }
                    }
                }
                else { // En cas d'erreur, affichage d'une pop-up
                    Toast.makeText(context, "Échec de l'inscription ! Veuillez réessayer", Toast.LENGTH_SHORT).show()
                }
        }
    }
}