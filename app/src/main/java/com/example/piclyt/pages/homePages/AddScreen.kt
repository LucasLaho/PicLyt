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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.piclyt.MainActivity
import com.example.piclyt.MainActivity.Companion.listAlbums
import com.example.piclyt.R
import com.example.piclyt.data.Album
import com.example.piclyt.fireBaseUtils.createAlbum
import com.example.piclyt.fireBaseUtils.getFriendsList
import com.example.piclyt.utils.CreateTextField
import com.example.piclyt.utils.createBottomNavigation
import com.google.firebase.firestore.FirebaseFirestore

// ########################## Ecran d'ajout d'album ######################### //

// Fonction principale de la page d'ajout d'album
@Composable
fun AddScreen(navController: NavController, context: Context, db: FirebaseFirestore, modifier: Modifier = Modifier) {
    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        createBottomNavigation(
            navController,
            context,
            modifier,
            true
        ) // Affichage de la barre de navigation

        /*var isPopUpVisible by remember { mutableStateOf(false) }
        var friendsList by remember { mutableStateOf(mutableListOf("")) }
        var checkedState by remember { mutableStateOf(BooleanArray(friendsList.size) { false }) }

        LaunchedEffect(Unit) {
            getFriendsList(MainActivity.authManager, db) { resultList ->
                friendsList.clear()
                friendsList.addAll(resultList)
                checkedState = BooleanArray(friendsList.size) {false}
            }
        }*/

        var isPublic by remember { mutableStateOf(true) }
        var nomAlbum by rememberSaveable { mutableStateOf("") }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp) // Ajustement de l'espace en fonction de la hauteur du BottomNavigation
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Ajout d'un album",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(5.dp),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.ExtraBold,
                    )
                }

                Spacer(modifier = Modifier.padding(top = 120.dp))

                CreateTextField(
                    label = "Nom de l'album",
                    onValueChange = { nomAlbum = it },
                ) // Champ de saisie du nom de l'album

                // Bouton pour choisir une image comme icône de l'album
                Button(
                    onClick = { /* Action pour choisir une image */ },
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text("Choisir une icône")
                }

                // Slider pour définir si l'album est public ou privé
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ){
                    Switch(
                        checked = isPublic,
                        onCheckedChange = { isPublic = it },
                        modifier = Modifier
                            .padding(end = 20.dp)
                    )
                    Text(
                        text = if (isPublic) "Public" else "Privé",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }

                /*Button(
                    onClick = {
                        isPopUpVisible = true
                    },
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text("Ajouter des amis")
                }

                if(isPopUpVisible) {
                    FriendsToAddListPopup(
                        context = context,
                        db = db,
                        friendsList = friendsList,
                        checkedState = checkedState) {result ->
                        isPopUpVisible = false
                        checkedState = result
                    }
                }*/

                Spacer(modifier = Modifier.padding(top = 80.dp))

                var test : Album = Album("test", R.drawable.ic_launcher_background)
                // Bouton de création de l'album
                Button(
                    onClick = {
                        createAlbum(navController, context, db, modifier, nomAlbum, isPublic){response ->
                            Toast.makeText(context, response, Toast.LENGTH_SHORT).show()
                        }
                        listAlbums.add(test)
                        Toast.makeText(context, "Ajout du nouvel album : " + test.name, Toast.LENGTH_SHORT).show()
                        navController.navigate("Home")
                    },
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text("Créer l'album")
                }
            }
        }
    }
}

/*@Composable
fun FriendsToAddListPopup(
    context: Context,
    db: FirebaseFirestore,
    friendsList: MutableList<String>,
    checkedState: BooleanArray,
    onClosePopup: (BooleanArray) -> Unit
) {
    var rememberedCheckedState by remember { mutableStateOf(checkedState) }
    AlertDialog(
        onDismissRequest = { onClosePopup(rememberedCheckedState) },
        confirmButton = {},
        title = { Text(text = "Mes amis", fontSize = 20.sp) },
        text = {
            Column {
                LazyColumn {
                    items(friendsList.size) { index ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = friendsList[index])
                            Checkbox(checked = rememberedCheckedState[index], onCheckedChange = {
                                rememberedCheckedState[index] = it
                            })
                        }
                    }
                }
            }
        }
    )
}*/