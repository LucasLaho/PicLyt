package com.example.piclyt.fireBaseUtils

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.example.piclyt.MainActivity.Companion.authManager
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
        // On check dans la collection usernames
        db.collection("usernames")
            // Si il y a déjà le même username que celui souhaité
            .whereEqualTo("username", usernameText)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    // Si le nom d'utilisateur existe déjà
                    Toast.makeText(context, "$usernameText est déjà utilisé", Toast.LENGTH_SHORT).show()
                } else {
                    // Si le nom d'utilisateur est disponible, on l'ajoute
                    db.collection("usernames").document(uid).set(user)
                        .addOnSuccessListener { documentReference ->
                            // Message de bienvenue et navigation à la homepage
                            Toast.makeText(context, "Bienvenue, $usernameText !", Toast.LENGTH_SHORT).show()
                            navController.navigate("Home") {
                                popUpTo("Username") { inclusive = true } // Suppression de l'historique de navigation
                            }
                        }
                        .addOnFailureListener { e ->
                            // Message d'erreur
                            Toast.makeText(context, "Erreur de connexion à la base de données", Toast.LENGTH_SHORT).show()
                        }
                }
            }
            .addOnFailureListener { e ->
                // Message d'erreur
                Toast.makeText(context, "Erreur de connexion à la base de données", Toast.LENGTH_SHORT).show()
            }
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