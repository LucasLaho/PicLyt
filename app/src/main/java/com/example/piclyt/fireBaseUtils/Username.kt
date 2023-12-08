package com.example.piclyt.fireBaseUtils

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.example.piclyt.MainActivity.Companion.authManager
import com.example.piclyt.MainActivity.Companion.db
import com.google.firebase.firestore.FirebaseFirestore

// ############################# Utilitaires pour la fonctionnalité de choix de nom d'utilisateur ########################## //

// Fonction permettant de choisir un nom d'utilisateur s'il n'existe pas déjà
fun Username(navController: NavController, context: Context, usernameText: String) {
    val currentUser = authManager.getAuth.currentUser
    val uid = currentUser?.uid
    val user = hashMapOf(
        "username" to usernameText
    )

    // On check dans la collection usernames
    db.collection("usernames")
        // Si il y a déjà le même username que celui souhaité
        .whereEqualTo("username", usernameText)
        .get()
        .addOnSuccessListener { querySnapshot ->
            if (!querySnapshot.isEmpty) {
                // Si le nom d'utilisateur existe déjà
                Toast.makeText(context, "$usernameText est déjà utilisé !!", Toast.LENGTH_SHORT).show()
            } else {

                // Exécution de l'ajout d'un compte à Firebase
                authManager.getAuth.createUserWithEmailAndPassword(authManager.getEmailText, authManager.getPasswordText)
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) { // En cas de réussite, redirection directement vers la page d'accueil
                            // Si le nom d'utilisateur est disponible, on l'ajoute
                            if (uid != null) {
                                db.collection("usernames").document(uid).set(user)
                                    .addOnSuccessListener { documentReference ->
                                        // Message de bienvenue et navigation à la homepage
                                        Toast.makeText(context, "Compte créé avec succès", Toast.LENGTH_SHORT).show()
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
                    }
            }
        }
        .addOnFailureListener { e ->
            // Message d'erreur
            Toast.makeText(context, "Erreur de connexion à la base de données", Toast.LENGTH_SHORT).show()
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