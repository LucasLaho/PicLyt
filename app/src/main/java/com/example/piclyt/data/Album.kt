package com.example.piclyt.data

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

// Modèle de données pour un album
data class Album(
    val name: String,
    val imageResource: Int,
    val isOpen : Boolean
) {
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
