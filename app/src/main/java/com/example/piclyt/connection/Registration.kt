package com.example.piclyt.connection

import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

fun Registration(navController: NavController, context: Context, auth: FirebaseAuth, emailText: String, passwordText: String) {

    if(TextUtils.isEmpty(emailText)) {
        Toast.makeText(context,"Veuillez renseigner votre adresse e-mail", Toast.LENGTH_SHORT).show()
    }
    else if(TextUtils.isEmpty(passwordText)) {
        Toast.makeText(context, "Veuillez renseigner votre mot de passe", Toast.LENGTH_SHORT).show()
    }
    else {
        auth.createUserWithEmailAndPassword(emailText, passwordText)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Compte créé avec succès", Toast.LENGTH_SHORT).show()
                    Firebase.auth.signOut()
                    navController.popBackStack()
                } else {
                    Toast.makeText(context, "Une erreur est survenue, veuillez réessayer", Toast.LENGTH_SHORT).show()
                }
            }
    }
}