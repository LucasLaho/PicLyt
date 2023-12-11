package com.example.piclyt.fireBaseUtils

import android.net.Uri
import androidx.navigation.NavController
import com.example.piclyt.MainActivity
import com.example.piclyt.MainActivity.Companion.authManager
import com.example.piclyt.data.AlbumData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

fun createAlbum(navController: NavController, db: FirebaseFirestore, nomAlbum: String, isPublic: Boolean, icon: Uri, callback: (String) -> Unit) {
    if (nomAlbum.isBlank()) {
        callback("Le nom de l'album ne peut pas être vide.")
        return
    }

    getUsername(authManager, db) {
        val currentUser = authManager.getAuth.currentUser
        val uid = currentUser?.uid
        val albumDataInfo = AlbumData(nomAlbum, icon, isPublic)
        
        db.collection(uid+nomAlbum).document("albumInfo").set(albumDataInfo)
            .addOnSuccessListener {
                callback("L'album $nomAlbum a bien été créé")
                db.collection(uid+"albums").document(uid+nomAlbum).set(albumDataInfo)
                MainActivity.listAlbumData.add(albumDataInfo)
                navController.navigate("Home")
            }
            .addOnFailureListener {
                // Message d'erreur
                callback("Erreur de connexion à la base de données")
            }
    }
}

fun getFriendsPublicAlbums(db: FirebaseFirestore, nomAlbum: String, isPublic: Boolean, callback: (MutableList<String>?) -> Unit) {
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

fun addFriendToAlbum(db: FirebaseFirestore, nomAlbum: String, username: String, callback: (String) -> Unit) {
    getUsername(authManager, db) { result ->
        val albumInfo = hashMapOf(
            "nom" to nomAlbum,
            "username" to result
        )
        val currentUser = authManager.getAuth.currentUser
        val uid = currentUser?.uid
        db.collection("users")
            .whereEqualTo("username", username)
            .get()
            .addOnSuccessListener { querySnapshot: QuerySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val documentSnapshot = querySnapshot.documents[0]
                    val uid = documentSnapshot.id
                    db.collection(uid + "albums").document(uid + nomAlbum).set(albumInfo)
                    callback("$username a bien été ajouté à l'album $nomAlbum")
                }
            }
    }
}