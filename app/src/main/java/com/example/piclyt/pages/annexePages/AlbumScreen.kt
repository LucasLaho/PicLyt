package com.example.piclyt.pages.annexePages

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.GroupAdd
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.piclyt.MainActivity
import com.example.piclyt.MainActivity.Companion.currentAlbumData
import com.example.piclyt.fireBaseUtils.addFriendToAlbum
import com.example.piclyt.fireBaseUtils.getFriendsList
import com.example.piclyt.utils.ConfirmDeleteDialog
import com.google.firebase.firestore.FirebaseFirestore

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumScreen(navController: NavController, context: Context, db: FirebaseFirestore, modifier: Modifier = Modifier) {

    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris -> currentAlbumData.updateSelectedImageUris(uris) }
    )

    var showDialog by remember { mutableStateOf(false) }
    var selectedImageUriToDelete by remember { mutableStateOf<Uri?>(null) }
    var isAddFriendPopupVisible by remember { mutableStateOf(false) }
    val friendsList by remember { mutableStateOf(mutableListOf("")) }

    getFriendsList(MainActivity.authManager, db) { resultList ->
        friendsList.clear()
        friendsList.addAll(resultList)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = currentAlbumData.name) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    // Bouton "+" pour ajouter des amis
                    IconButton(onClick = {
                        isAddFriendPopupVisible = true
                    }) {
                        Icon(Icons.Default.GroupAdd, contentDescription = null)
                    }

                    if(isAddFriendPopupVisible){
                        getFriendsList(MainActivity.authManager, db) { resultList ->
                            friendsList.clear()
                            friendsList.addAll(resultList)
                        }
                        AddFriendPopup(
                            context = context,
                            db = db,
                            friendsList = friendsList,
                            onClosePopup = { isAddFriendPopupVisible = false }
                        )
                    }

                    // Bouton "+" pour lancer la sélection d'images
                    IconButton(onClick = {
                        multiplePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo)
                        )
                    }) {
                        Icon(Icons.Default.AddPhotoAlternate, contentDescription = null)
                    }
                }
            )
        }
    ) {

        LazyColumn(
            content = {
                // Espacement entre la top bar et les éléments
                item { Spacer(modifier = Modifier.height(50.dp)) }

                items(currentAlbumData.selectedImageUris) { uri ->
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
                    currentAlbumData.removeSelectedImage(selectedImageUriToDelete!!) // "!!" pour éviter le null exception
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

@Composable
fun AddFriendPopup(
    context: Context,
    db: FirebaseFirestore,
    friendsList: MutableList<String>,
    onClosePopup: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onClosePopup() },
        confirmButton = {},
        title = { Text(text = "Amis à ajouter à l'album", fontSize = 20.sp) },
        text = {
            Column {
                LazyColumn {
                    items(friendsList.size) { index ->
                        FriendItem(context, db, friendsList[index])
                    }
                }
            }
        }
    )
}

@Composable
fun FriendItem(
    context: Context,
    db: FirebaseFirestore,
    username: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "$username")
        IconButton(onClick = {
            val nomAlbum = "Vacances" //temporaire, à supprimer quand implémentation
            addFriendToAlbum(db, nomAlbum, username){response ->
                Toast.makeText(context, response, Toast.LENGTH_SHORT).show()
            }
        }) {
            Icon(Icons.Filled.GroupAdd, contentDescription = "Ajouter ami")
        }
    }
}