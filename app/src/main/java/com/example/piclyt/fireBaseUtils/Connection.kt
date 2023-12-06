package com.example.piclyt.fireBaseUtils

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.example.piclyt.utils.testDataFields
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

// ############################# Utilitaires pour la fonctionnalité de connexion ########################## //

// Fonction permettant d'établir une connexion à Firebase depuis l'écran de connexion
fun Connection(navController: NavController, context: Context, authManager: AuthManager, db: FirebaseFirestore, emailText: String, passwordText: String) {

    if(testDataFields(context, emailText, passwordText)){
        // Exécution de la connexion à Firebase
        authManager.getAuth.signInWithEmailAndPassword(emailText, passwordText)
            .addOnCompleteListener() {

                    task -> if (task.isSuccessful) { // En cas de réussite, redirection
                val currentUser = authManager.getAuth.currentUser
                if (currentUser != null) { // Test d'une possible connexion existante
                    val existingUsername = db.collection("usernames")
                        .document(currentUser.uid)
                        .get()
                        .addOnSuccessListener { document ->
                            if (document!=null && document.exists()) { // Si username existant, direction homepage
                                navController.navigate("Home") {
                                    popUpTo("connection") { inclusive = true } // Suppression de l'historique de navigation
                                }
                            }
                            else { // Sinon, direction username page
                                navController.navigate("Username") {
                                    popUpTo("connection") { inclusive = true } // Suppression de l'historique de navigation
                                }
                            }
                        }
                }
            }
            else { // En cas d'erreur, on affiche une pop-up
                Toast.makeText(context, "Échec de la connexion ! Veuillez réessayer", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

