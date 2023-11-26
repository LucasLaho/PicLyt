package com.example.piclyt.utils

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

// Utilité : Ici, on retrouve toutes les fonctions et classes utiles pour toutes les pages au bon déroulement de l'application !

// ############################# Listes des données partagées dans l'application ########################## //

// Classe définissant une liste d'images ajoutées depuis la galerie
class ImageViewModel : ViewModel() {
    private var _selectedImageUris by mutableStateOf<List<Uri>>(emptyList())
    val selectedImageUris: List<Uri>
        get() = _selectedImageUris

    fun updateSelectedImageUris(uris: List<Uri>) {
        _selectedImageUris += uris
    }

    fun removeSelectedImage(uri: Uri) {
        _selectedImageUris = _selectedImageUris.filterNot { it == uri }
    }
}

// ############################# Listes des fonctions de tests ########################## //

// Fonction de test de champs de texte des pages de connexion et d'inscritpion
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