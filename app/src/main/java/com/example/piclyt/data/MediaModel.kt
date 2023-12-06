package com.example.piclyt.data

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

// ############################# Listes des données partagées dans l'application ########################## //

// Classe définissant une liste d'images ajoutées depuis la galerie
class MediaModel : ViewModel() {
    companion object {
        private var instance: MediaModel? = null

        fun getInstance(): MediaModel {
            if (instance == null) {
                instance = MediaModel()
            }
            return instance!!
        }
    }

    private var _selectedImageUris by mutableStateOf<List<Uri>>(emptyList())
    val selectedImageUris: List<Uri>
        get() = _selectedImageUris

    fun updateSelectedImageUris(uris: List<Uri>) {
        _selectedImageUris += uris
    }

    fun removeSelectedImage(uri: Uri) {
        _selectedImageUris = _selectedImageUris.filterNot { it == uri }
    }
}

