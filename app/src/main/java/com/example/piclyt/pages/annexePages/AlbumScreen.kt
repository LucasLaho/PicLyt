package com.example.piclyt.pages.annexePages

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.piclyt.MainActivity.Companion.listMedias
import com.example.piclyt.utils.ConfirmDeleteDialog
import com.google.firebase.firestore.FirebaseFirestore

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumScreen(navController: NavController, context: Context, db: FirebaseFirestore, modifier: Modifier = Modifier) {

    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris -> listMedias.updateSelectedImageUris(uris) }
    )

    var showDialog by remember { mutableStateOf(false) }
    var selectedImageUriToDelete by remember { mutableStateOf<Uri?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Media") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    // Bouton "+" pour lancer la sélection d'images
                    IconButton(onClick = {
                        multiplePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo)
                        )
                    }) {
                        Icon(Icons.Default.Add, contentDescription = null)
                    }
                }
            )
        }
    ) {

        LazyColumn(
            content = {
                // Espacement entre la top bar et les éléments
                item { Spacer(modifier = Modifier.height(50.dp)) }

                items(listMedias.selectedImageUris) { uri ->
                    AsyncImage(
                        model = uri,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                // Afficher la boîte de dialogue avant de supprimer l'image
                                selectedImageUriToDelete = uri
                                showDialog = true
                            },
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        )

        // Exécuter la fonction de suppression si showDialog est true
        if (showDialog) {
            ConfirmDeleteDialog(
                onConfirm = {
                    // Supprimer l'image ici
                    listMedias.removeSelectedImage(selectedImageUriToDelete!!) // !! pour éviter le null exception
                    showDialog = false
                    selectedImageUriToDelete = null
                },
                onCancel = {
                    // Annuler la suppression
                    showDialog = false
                    selectedImageUriToDelete = null
                }
            )
        }
    }
}

