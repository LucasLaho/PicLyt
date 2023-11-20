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
import androidx.navigation.NavController
import com.example.piclyt.utils.AddHeaderText
import com.example.piclyt.utils.createBottomNavigation
import com.google.firebase.auth.FirebaseAuth

// ########################## Ecran d'accueil ######################### //
@Composable
fun AddScreen(navController: NavController, context: Context, auth: FirebaseAuth, modifier: Modifier = Modifier) {
    createBottomNavigation(navController, context, modifier, true) // Affichage de la navigation

    var selectedImage by remember {
        mutableStateOf<Uri?>(null)
    }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
        uri -> selectedImage = uri
    }

    Column {
        AddHeaderText()
        //PickImageFromGallery(selectedImage) { launcher.launch("image/*") }
    }
}

