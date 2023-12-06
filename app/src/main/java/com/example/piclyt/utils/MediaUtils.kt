package com.example.piclyt.utils

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.piclyt.data.MediaModel

// ############################# Liste des fonctions de la page d'ajout ########################## //

// Fonctionnalité d'ajout de photo à partir du portable (NE FONCTIONNE PAS correctement !!!)
@Composable
fun PickImageFromGallery(viewModel: MediaModel) {

    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult (
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = {uris -> viewModel.updateSelectedImageUris(uris)}
    )

    LazyColumn{
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                Button(onClick = {
                    multiplePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo)
                    )
                }) {
                    Text(text = "Pick media from gallery")
                }
            }
        }

        items (viewModel.selectedImageUris) { uri ->
            AsyncImage (
                model = uri,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.removeSelectedImage(uri) }, // On supprime l'image si elle est touchée
                contentScale = ContentScale.Crop,
            )

        }
    }
}
