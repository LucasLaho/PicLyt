package com.example.piclyt.utils

import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

// Utilité : Ici, on retrouve toutes les fonctions et classes utiles pour toutes les pages au bon déroulement de l'application !
// ############################# Listes des fonctions de tests ########################## //

// Fonction de test de champs de texte des pages de connexion et d'inscritpion
fun testDataFields(context: Context, emailText: String, passwordText: String): Boolean{

    if(TextUtils.isEmpty(emailText)) { // Vérification que le champ email n'est pas vide
        Toast.makeText(context,"Veuillez renseigner votre adresse e-mail", Toast.LENGTH_SHORT).show()
        return false
    }

    if(TextUtils.isEmpty(passwordText)) { // Vérification que le champ mot de passe n'est pas vide
        Toast.makeText(context, "Veuillez renseigner votre mot de passe", Toast.LENGTH_SHORT).show()
        return false
    }

    if (passwordText.length<6) {
        Toast.makeText(context, "Échec de l'inscription ! Mot de passe de 6 caractères minimum", Toast.LENGTH_SHORT).show()
        return false
    }

    return true // Retour d'une validation pour passer à la navigation
}

fun isUsernameAvailable(db: FirebaseFirestore, usernameText: String, callback: (Boolean) -> Unit) {
    db.collection("users")
        .whereEqualTo("username", usernameText)
        .get()
        .addOnSuccessListener { querySnapshot ->
            // Si la liste est vide, le nom d'utilisateur est disponible
            callback(querySnapshot.isEmpty)
        }
        .addOnFailureListener {
            // En cas d'erreur, on considère que le nom d'utilisateur est indisponible
            callback(false)
        }
}