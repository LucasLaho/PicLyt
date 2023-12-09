package com.example.piclyt.fireBaseUtils

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.example.piclyt.MainActivity.Companion.authManager
import com.example.piclyt.utils.isUsernameAvailable
import com.google.firebase.firestore.FirebaseFirestore

// ############################# Utilitaires pour la fonctionnalité de choix de nom d'utilisateur ########################## //

// Fonction permettant de choisir un nom d'utilisateur s'il n'existe pas déjà
fun Username(navController: NavController, context: Context, db: FirebaseFirestore, usernameText: String) {
    val currentUser = authManager.getAuth.currentUser
    val uid = currentUser?.uid
    val user = hashMapOf(
        "username" to usernameText
    )

    // Si on est bien connectés
    if(uid!=null) {

        isUsernameAvailable(db, usernameText) { isAvailable ->

            if (isAvailable && addUser(db, uid, user, context)) {

                // Message de bienvenue et navigation à la homepage
                Toast.makeText(context, "Bienvenue, $usernameText !", Toast.LENGTH_SHORT).show()
                navController.navigate("Home") {
                    popUpTo("Username") { inclusive = true } // Suppression de l'historique de navigation
                }

            } else {
                // Si le nom d'utilisateur existe déjà
                Toast.makeText(context, "$usernameText est déjà utilisé", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

fun addUser(db: FirebaseFirestore, uid: String, user: Map<String, Any>, context: Context): Boolean {
    try {

        // Ajoute le nouvel utilisateur
        db.collection("users").document(uid).set(user)
            .addOnSuccessListener {}
            .addOnFailureListener {
                // Message d'erreur
                Toast.makeText(context, "Erreur de connexion à la base de données", Toast.LENGTH_SHORT).show()
            }

        return true // La tentative d'ajout a réussi
    } catch (e: Exception) {
        // En cas d'erreur inattendue
        Toast.makeText(context, "Erreur lors de l'ajout de l'utilisateur", Toast.LENGTH_SHORT).show()
        return false // La tentative d'ajout a échoué
    }
}

// Fonction qui permet de récupérer le nom d'utilisateur de l'utilisateur connecté
fun getUsername(authManager: AuthManager, db: FirebaseFirestore): String {
    val currentUser = authManager.getAuth.currentUser
    val uid = currentUser?.uid
    var username = ""
    if(uid!=null) {
        db.collection("usernames")
            .document(uid)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val temp = documentSnapshot.getString("username")
                    if (temp!=null) {
                        username = temp
                    }
                }
            }
    }
    return username
}