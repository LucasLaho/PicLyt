package com.example.piclyt.homepage

import android.content.Context
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

fun Deconnection(navController: NavController, context: Context, auth: FirebaseAuth) {
    Firebase.auth.signOut()
    navController.navigate("connection") {
        popUpTo("homepage") { inclusive = true }
    }
}