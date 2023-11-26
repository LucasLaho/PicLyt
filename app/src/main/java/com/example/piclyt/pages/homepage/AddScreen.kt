package com.example.piclyt.pages.homepage

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.piclyt.ui.theme.PicLytTheme
import com.example.piclyt.utils.createBottomNavigation
import com.example.piclyt.utils.createHeaderText
import com.google.firebase.auth.FirebaseAuth

// ########################## Ecran d'ajout d'album ######################### //

// Fonction principale de la page d'ajout d'album
@Composable
fun AddScreen(navController: NavController, context: Context, auth: FirebaseAuth, modifier: Modifier = Modifier) {
    createBottomNavigation(navController, context, modifier, true) // Affichage de la barre de navigation

    // Image selectionné dans la galerie du portable
    var selectedImage by remember {
        mutableStateOf<Uri?>(null)
    }

    // Chemin de l'image depuis la galerie pour l'afficher
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
        uri -> selectedImage = uri
    }

    Column {
        createHeaderText("Ajout d'un album") // Affichage du titre de la page
        //PickImageFromGallery(selectedImage) { launcher.launch("image/*") } // Bouton d'ajout d'une image depuis la galerie
    }
}

// ########################## Preview de l'ecran d'ajout d'album ################ //
@Preview(showBackground = true)
@Composable
fun AddScreenPreview() {
    PicLytTheme {
        AddScreen(navController = rememberNavController(), LocalContext.current, FirebaseAuth.getInstance())
    }
}
