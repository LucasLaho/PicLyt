package com.example.piclyt.fireBaseUtils

import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

fun getFriendsList(authManager: AuthManager, db: FirebaseFirestore, callback: (MutableList<String>) -> Unit) {
    val currentUser = authManager.getAuth.currentUser
    val uid = currentUser?.uid
    if(uid!=null) {
        db.collection(uid+"friendslist")
            .whereNotEqualTo(FieldPath.documentId(), uid)
            .get()
            .addOnSuccessListener { querySnapshot: QuerySnapshot ->
                val friendList = mutableListOf<String>()
                for (documentSnapshot in querySnapshot.documents) {
                    val friend = documentSnapshot.getString("username")
                    if (friend != null) {
                        friendList.add(friend)
                    }
                }
                callback(friendList)
            }
            .addOnFailureListener {
                callback(mutableListOf(""))
            }
    } else {
        callback(mutableListOf(""))
    }
}

fun getFriendRequestsList(authManager: AuthManager, db: FirebaseFirestore, callback: (MutableList<String>) -> Unit) {
    val currentUser = authManager.getAuth.currentUser
    val uid = currentUser?.uid
    if(uid!=null) {
        db.collection(uid+"friendrequestslist")
            .whereNotEqualTo(FieldPath.documentId(), uid)
            .get()
            .addOnSuccessListener { querySnapshot: QuerySnapshot ->
                val friendRequestsList = mutableListOf<String>()
                for (documentSnapshot in querySnapshot.documents) {
                    val friendRequest = documentSnapshot.getString("username")
                    if (friendRequest != null) {
                        friendRequestsList.add(friendRequest)
                    }
                }
                callback(friendRequestsList)
            }
            .addOnFailureListener {
                callback(mutableListOf(""))
            }
    } else {
        callback(mutableListOf(""))
    }
}

fun deleteFriend(authManager: AuthManager, db: FirebaseFirestore, username: String, callback: (String) -> Unit) {
    val currentUser = authManager.getAuth.currentUser
    val uid = currentUser?.uid
    var otherUid: String
    if(uid!=null) {
        db.collection(uid+"friendslist")
            .whereEqualTo("username", username)
            .get()
            .addOnSuccessListener { querySnapshot: QuerySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val documentSnapshot = querySnapshot.documents[0]
                    otherUid = documentSnapshot.id
                    documentSnapshot.reference.delete()
                    callback(username+" n'est plus mon ami(e)")
                    db.collection(otherUid + "friendslist")
                        .whereEqualTo(FieldPath.documentId(), uid)
                        .get()
                        .addOnSuccessListener { querySnapshot: QuerySnapshot ->
                            if (!querySnapshot.isEmpty) {
                                querySnapshot.documents[0].reference.delete()
                            }
                        }
                }
            }
    }
}

fun addFriend(authManager: AuthManager, db: FirebaseFirestore, username: String, callback: (String) -> Unit) {
    getUsername(authManager, db) {result ->
        val myUsername = result
        val myInfo = hashMapOf(
            "username" to myUsername
        )
        val currentUser = authManager.getAuth.currentUser
        val uid = currentUser?.uid
        var otherUid: String
        if(uid!=null) {
            db.collection("users")
                .whereEqualTo("username", username)
                .get()
                .addOnSuccessListener { querySnapshot: QuerySnapshot ->
                    if (!querySnapshot.isEmpty) {
                        val documentSnapshot = querySnapshot.documents[0]
                        otherUid = documentSnapshot.id
                        if (uid==otherUid){
                            callback("Je ne peux pas m'ajouter en ami(e) !")
                            return@addOnSuccessListener
                        }
                        db.collection(uid+"friendslist")
                            .whereEqualTo(FieldPath.documentId(), otherUid)
                            .get()
                            .addOnSuccessListener { querySnapshot: QuerySnapshot ->
                                if (querySnapshot.isEmpty) {
                                    db.collection(otherUid + "friendrequestslist")
                                        .whereEqualTo(FieldPath.documentId(), uid)
                                        .get()
                                        .addOnSuccessListener { querySnapshot: QuerySnapshot ->
                                            if (querySnapshot.isEmpty) {
                                                db.collection(otherUid+"friendrequestslist").document(uid).set(myInfo)
                                                callback("L'invitation a été envoyée")
                                                return@addOnSuccessListener
                                            }
                                            else {
                                                callback("Une invitation a déjà été envoyée")
                                                return@addOnSuccessListener
                                            }
                                        }
                                }
                                else {
                                    callback(username+ " est déjà mon ami(e) !")
                                    return@addOnSuccessListener
                                }
                            }
                    }
                    else {
                        callback("Cet utilisateur n'existe pas")
                        return@addOnSuccessListener
                    }
                }
        }
    }
}

fun acceptFriendRequest(authManager: AuthManager, db: FirebaseFirestore, username: String, callback: (String) -> Unit) {
    getUsername(authManager, db) { result ->
        val myUsername = result
        val myInfo = hashMapOf(
            "username" to myUsername
        )
        val currentUser = authManager.getAuth.currentUser
        val uid = currentUser?.uid
        var otherUid: String
        if (uid != null) {
            db.collection(uid + "friendrequestslist")
                .whereEqualTo("username", username)
                .get()
                .addOnSuccessListener { querySnapshot: QuerySnapshot ->
                    if (!querySnapshot.isEmpty) {
                        val documentSnapshot = querySnapshot.documents[0]
                        otherUid = documentSnapshot.id
                        val friendInfo = hashMapOf(
                            "username" to username
                        )
                        documentSnapshot.reference.delete()
                        db.collection(uid + "friendslist").document(otherUid).set(friendInfo)
                        db.collection(otherUid+"friendslist").document(uid).set(myInfo)
                        callback(username+" a été ajouté à la liste d'amis")
                        db.collection(otherUid + "friendrequestslist")
                            .whereEqualTo("username", myUsername)
                            .get()
                            .addOnSuccessListener { querySnapshot: QuerySnapshot ->
                                if (!querySnapshot.isEmpty) {
                                    val documentSnapshot = querySnapshot.documents[0]
                                    documentSnapshot.reference.delete()
                                }
                            }
                    }
                }
        }
    }
}

fun denyFriendRequest(authManager: AuthManager, db: FirebaseFirestore, username: String, callback: (String) -> Unit) {
    val currentUser = authManager.getAuth.currentUser
    val uid = currentUser?.uid
    if (uid != null) {
        db.collection(uid + "friendrequestslist")
            .whereEqualTo("username", username)
            .get()
            .addOnSuccessListener { querySnapshot: QuerySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val documentSnapshot = querySnapshot.documents[0]
                    documentSnapshot.reference.delete()
                    callback(username+" a été refusé")
                }
            }
    }
}
