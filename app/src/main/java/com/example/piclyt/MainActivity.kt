package com.example.piclyt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.piclyt.data.MediaModel
import com.example.piclyt.fireBaseUtils.AuthManager
import com.example.piclyt.fireBaseUtils.PicLytNavHost
import com.example.piclyt.ui.theme.PicLytTheme

class MainActivity : ComponentActivity() {
    companion object {
        lateinit var authManager: AuthManager
        val listMedias = MediaModel.getInstance() // Liste des images enregistrées par l'utilisateur
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authManager = AuthManager(this)

        setContent {
            PicLytTheme {
                PicLytNavHost(this)
            }
        }
    }
}


