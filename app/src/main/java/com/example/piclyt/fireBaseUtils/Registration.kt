package com.example.piclyt.fireBaseUtils

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.example.piclyt.utils.testDataFields

// ############################# Utilitaires pour la fonctionnalité d'inscription ########################## //

// Fonction permettant d'établir une inscription à Firebase depuis l'écran d'inscription
fun Registration(navController: NavController, context: Context, authManager: AuthManager, emailText: String, passwordText: String) {

    if(testDataFields(context, emailText, passwordText)){
        Toast.makeText(context, "Informations correctes", Toast.LENGTH_SHORT).show()
        navController.navigate("Username") {
            popUpTo("connection") { inclusive = true }
        }
        authManager.initLogin(emailText,passwordText)
    }
}