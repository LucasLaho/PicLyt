package com.example.piclyt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.piclyt.fireBaseUtils.PicLytNavHost
import com.example.piclyt.ui.theme.PicLytTheme
import com.example.piclyt.utils.ImageViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        setContent {
            PicLytTheme {
                val viewModel: ImageViewModel = viewModel() // Liste des images enregistrées par l'utilisateur tranférées à toutes les pages via la navigation
                PicLytNavHost(this, auth, viewModel)
            }
        }
    }
}