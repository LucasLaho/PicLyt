package com.example.piclyt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.piclyt.data.Album
import com.example.piclyt.fireBaseUtils.AuthManager
import com.example.piclyt.fireBaseUtils.PicLytNavHost
import com.example.piclyt.ui.theme.PicLytTheme

class MainActivity : ComponentActivity() {
    companion object {
        lateinit var authManager: AuthManager
        lateinit var currentAlbum : Album
        val album1 : Album = Album("Vacances", R.drawable.ic_launcher_background, false)
        val album2 : Album = Album("Famille", R.drawable.ic_google, false)
        val album3 : Album = Album("Mariage", R.drawable.ic_launcher_background, false)
        val album4 : Album = Album("Fêtes", R.drawable.ic_facebook, false)
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


