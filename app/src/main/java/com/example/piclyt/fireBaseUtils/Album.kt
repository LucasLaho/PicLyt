package com.example.piclyt.fireBaseUtils

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.piclyt.MainActivity
import com.example.piclyt.MainActivity.Companion.authManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

fun createAlbum(navController: NavController, context: Context, db: FirebaseFirestore, modifier: Modifier = Modifier, nomAlbum: String, isPublic: Boolean, callback: (String) -> Unit) {
    getUsername(authManager, db) {result ->
        val currentUser = MainActivity.authManager.getAuth.currentUser
        val uid = currentUser?.uid
        val username = result
        val albumInfo = hashMapOf(
            "username" to username,
            "nom" to nomAlbum,
            "isPublic" to isPublic
        )
        db.collection(uid+nomAlbum).document("albumInfo").set(albumInfo)
            .addOnSuccessListener {
                callback("L'album "+nomAlbum+" a bien été créé")
                db.collection(uid+"albums").document(uid+nomAlbum).set(albumInfo)
            }
            .addOnFailureListener {
                // Message d'erreur
                Toast.makeText(context, "Erreur de connexion à la base de données", Toast.LENGTH_SHORT).show()
            }
    }
}

fun getFriendsPublicAlbums(navController: NavController, context: Context, db: FirebaseFirestore, modifier: Modifier = Modifier, nomAlbum: String, isPublic: Boolean, callback: (MutableList<String>?) -> Unit) {
    var friendsPublicAlbums = mutableListOf<String>()
    getFriendsList(authManager,db) {friendsList ->
        for(friend in friendsList) {
            db.collection("users")
                .whereEqualTo("username", friend)
                .get()
                .addOnSuccessListener { querySnapshot: QuerySnapshot ->
                    if (!querySnapshot.isEmpty) {
                        val documentSnapshot = querySnapshot.documents[0]
                        val uid = documentSnapshot.id
                        db.collection(uid+"albums")
                            .whereEqualTo("isPublic", true)
                            .get()
                            .addOnSuccessListener {querySnapshot: QuerySnapshot ->
                                if (!querySnapshot.isEmpty) {
                                    for (query in querySnapshot) {
                                        friendsPublicAlbums.add(uid+query.get("nomAlbum").toString())
                                    }
                                }
                            }
                    }
                }
        }
        callback(friendsPublicAlbums)
    }
}

fun addFriendToAlbum(context: Context, db: FirebaseFirestore, nomAlbum: String, username: String, callback: (String) -> Unit) {
    getUsername(authManager, db) { result ->
        val albumInfo = hashMapOf(
            "nom" to nomAlbum,
            "username" to result
        )
        val currentUser = MainActivity.authManager.getAuth.currentUser
        val uid = currentUser?.uid
        db.collection("users")
            .whereEqualTo("username", username)
            .get()
            .addOnSuccessListener { querySnapshot: QuerySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val documentSnapshot = querySnapshot.documents[0]
                    val uid = documentSnapshot.id
                    db.collection(uid + "albums").document(uid + "nomAlbum").set(albumInfo)
                    callback(username+" a bien été ajouté à l'album "+nomAlbum)
                }
            }
    }
}