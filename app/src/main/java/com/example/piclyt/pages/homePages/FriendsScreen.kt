package com.example.piclyt.pages.homePages

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.GroupAdd
import androidx.compose.material.icons.filled.GroupRemove
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.piclyt.MainActivity.Companion.authManager
import com.example.piclyt.fireBaseUtils.acceptFriendRequest
import com.example.piclyt.fireBaseUtils.addFriend
import com.example.piclyt.fireBaseUtils.deleteFriend
import com.example.piclyt.fireBaseUtils.denyFriendRequest
import com.example.piclyt.fireBaseUtils.getFriendRequestsList
import com.example.piclyt.fireBaseUtils.getFriendsList
import com.example.piclyt.utils.createBottomNavigation
import com.google.firebase.firestore.FirebaseFirestore

// ########################## Ecran d'Amis ######################### //
// Utilité : Ici, on pourra voir les albums publics de nos amis.

// Fonction principale de l'écran d'Amis où on retrouvera les albums publics de nos amis
@Composable
fun FriendsScreen(navController: NavController, context: Context, db: FirebaseFirestore, modifier: Modifier = Modifier) {
    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        createBottomNavigation(
            navController,
            context,
            modifier,
            true
        ) // Affichage de la barre de navigation

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp) // Ajustement de l'espace en fonction de la hauteur du BottomNavigation
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    var isAddPopupVisible by remember { mutableStateOf(false) }
                    var friendRequests by remember { mutableStateOf(mutableListOf("")) }

                    var isListPopupVisible by remember { mutableStateOf(false) }
                    var friendsList by remember { mutableStateOf(mutableListOf("")) }

                    getFriendsList(authManager, db) { resultList ->
                        friendsList.clear()
                        friendsList.addAll(resultList)
                    }

                    getFriendRequestsList(authManager, db) { resultList ->
                        friendRequests.clear()
                        friendRequests.addAll(resultList)
                    }

                    // Bouton pop up liste d'amis
                    IconButton(onClick = { isListPopupVisible = true }) {
                        Icon(Icons.Filled.Group, contentDescription = "Liste d'amis")
                    }

                    // Si pop up pour liste amis est visible
                    if (isListPopupVisible) {
                        getFriendsList(authManager, db) { resultList ->
                            friendsList.clear()
                            friendsList.addAll(resultList)
                        }
                        FriendsListPopup(
                            context = context,
                            db = db,
                            friendsList = friendsList,
                            onClosePopup = { isListPopupVisible = false }
                        )
                    }

                    Text (
                        text = "Amis",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(5.dp),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.ExtraBold,
                    )

                    // Bouton pop up ajouter ami
                    IconButton(onClick = { isAddPopupVisible = true }) {
                        Icon(Icons.Filled.GroupAdd, contentDescription = "Ajouter un ami")
                    }

                    // Si pop up pour ajouter ami est visible
                    if (isAddPopupVisible) {
                        AddFriendPopup(
                            context = context,
                            db = db,
                            friendRequests = friendRequests,
                            onClosePopup = { isAddPopupVisible = false }
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Text (
                    text = "Albums publics de mes amis",
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .padding(start = 20.dp),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                )
                LazyColumn() {
                    /* Ajouter ici albums publics des amis */
                }
            }
        }
    }
}

@Composable
fun FriendsListPopup(
    context: Context,
    db: FirebaseFirestore,
    friendsList: MutableList<String>,
    onClosePopup: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onClosePopup() },
        confirmButton = {},
        title = { Text(text = "Mes amis", fontSize = 20.sp) },
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
    var isRemovePopupVisible by remember { mutableStateOf(false)}

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "$username")
        IconButton(onClick = { isRemovePopupVisible = true }) {
            Icon(Icons.Filled.GroupRemove, contentDescription = "Supprimer ami")
        }
    }

    // Si pop up pour supprimer ami est visible
    if (isRemovePopupVisible) {
        RemoveFriendPopup(
            context = context,
            db = db,
            username = username,
            onClosePopup = { isRemovePopupVisible = false }
        )
    }
}

@Composable
fun RemoveFriendPopup(
    context: Context,
    db: FirebaseFirestore,
    username: String,
    onClosePopup: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onClosePopup() },
        confirmButton = {},
        title = { Text(text = "Supprimer un ami", fontSize = 20.sp) },
        text = {
            Column {
                Text(text = "Souhaitez-vous supprimer $username de vos amis ?")
                Spacer(modifier = Modifier.padding(top = 20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconButton(onClick = {
                        deleteFriend(authManager, db, username) { response ->
                            Toast.makeText(context, response, Toast.LENGTH_SHORT).show()
                            onClosePopup()
                        }
                    }) {
                        Icon(Icons.Filled.Check, contentDescription = "Accepter")
                    }
                    IconButton(onClick = { onClosePopup() }) {
                        Icon(Icons.Filled.Clear, contentDescription = "Refuser")
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AddFriendPopup(
    context: Context,
    db: FirebaseFirestore,
    friendRequests: MutableList<String>,
    onClosePopup: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onClosePopup() },
        confirmButton = {},
        title = { Text(text = "Amis", fontSize = 20.sp) },
        text = {
            var usernameToAdd by remember { mutableStateOf("") }
            Column {
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Text(text = "Ajouter un ami", fontSize = 17.sp)
                Spacer(modifier = Modifier.padding(top = 15.dp))
                TextField(
                    value = usernameToAdd,
                    onValueChange = { it -> usernameToAdd = it },
                    label = { Text("Nom d'utilisateur") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    singleLine = true
                )
                Button(
                    onClick = {
                        addFriend(authManager, db, usernameToAdd) { response ->
                            Toast.makeText(context, response, Toast.LENGTH_SHORT).show()
                            usernameToAdd=""
                        }
                              },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text("Ajouter")
                }
                Spacer(modifier = Modifier.padding(top = 40.dp))
                Text(text = "Demandes d'ami", fontSize = 17.sp)
                Spacer(modifier = Modifier.padding(top = 10.dp))
                LazyColumn {
                    items(friendRequests.size) { index ->
                        FriendRequestItem(context, db, friendRequests[index]) {requestToDelete ->
                            friendRequests.drop(friendRequests.indexOf(requestToDelete))
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun FriendRequestItem(
    context: Context,
    db: FirebaseFirestore,
    username: String,
    callback: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "$username")
        Row {
            IconButton(onClick = {
                acceptFriendRequest(authManager, db, username) { response ->
                    Toast.makeText(context, response, Toast.LENGTH_SHORT).show()
                    callback(username)
                }
            }) {
                Icon(Icons.Filled.Check, contentDescription = "Accepter")
            }
            //Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = {
                denyFriendRequest(authManager, db, username) { response ->
                    Toast.makeText(context, response, Toast.LENGTH_SHORT).show()
                    callback(username)
                }
            }) {
                Icon(Icons.Filled.Clear, contentDescription = "Refuser")
            }
        }
    }
}