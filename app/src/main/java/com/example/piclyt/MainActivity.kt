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
        val listAlbums: MutableList<Album> = mutableListOf()

        lateinit var authManager: AuthManager
        lateinit var currentAlbum: Album

        val album1: Album = Album("Vacances", R.drawable.ic_launcher_background)
        val album2: Album = Album("Famille", R.drawable.ic_google)
        val album3: Album = Album("Mariage", R.drawable.ic_launcher_background)
        val album4: Album = Album("Fêtes", R.drawable.ic_facebook)

        init {
            // Initialisation des albums
            listAlbums.add(album1)
            listAlbums.add(album2)
            listAlbums.add(album3)
            listAlbums.add(album4)
        }
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