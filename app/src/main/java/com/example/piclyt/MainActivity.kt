package com.example.piclyt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.piclyt.data.AlbumData
import com.example.piclyt.fireBaseUtils.AuthManager
import com.example.piclyt.fireBaseUtils.PicLytNavHost
import com.example.piclyt.ui.theme.PicLytTheme

class MainActivity : ComponentActivity() {
    companion object {
        val listAlbumData: MutableList<AlbumData> = mutableListOf()

        lateinit var authManager: AuthManager
        lateinit var currentAlbumData: AlbumData

        val albumData1: AlbumData = AlbumData("Vacances", R.drawable.ic_launcher_background, true)
        val albumData2: AlbumData = AlbumData("Famille", R.drawable.ic_google, true)
        val albumData3: AlbumData = AlbumData("Mariage", R.drawable.ic_launcher_background, true)
        val albumData4: AlbumData = AlbumData("Fêtes", R.drawable.ic_facebook, true)

        init {
            // Initialisation des albums
            listAlbumData.add(albumData1)
            listAlbumData.add(albumData2)
            listAlbumData.add(albumData3)
            listAlbumData.add(albumData4)
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