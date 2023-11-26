package com.example.piclyt.utils

import android.content.Context
import android.text.TextUtils
import android.widget.Toast

// Utilité : Ici, on retrouve toutes les fonctions utiles pour toutes les pages au bon déroulement de l'application !

// ############################# Listes des fonctions de tests ########################## //
fun testDataFields(context: Context, emailText: String, passwordText: String): Boolean{

    if(TextUtils.isEmpty(emailText)) { // Vérification que le champ email n'est pas vide
        Toast.makeText(context,"Veuillez renseigner votre adresse e-mail", Toast.LENGTH_SHORT).show()
        return false
    }

    else if(TextUtils.isEmpty(passwordText)) { // Vérification que le champ mot de passe n'est pas vide
        Toast.makeText(context, "Veuillez renseigner votre mot de passe", Toast.LENGTH_SHORT).show()
        return false
    }

    return true // Retour d'une validation pour passer à la navigation
}