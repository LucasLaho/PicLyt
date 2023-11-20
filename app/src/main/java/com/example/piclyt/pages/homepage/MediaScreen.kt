package com.example.piclyt.pages.homepage

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.piclyt.pages.annexe.ProfileCard
import com.example.piclyt.pages.annexe.SettingsHeaderText
import com.example.piclyt.ui.theme.PicLytTheme
import com.example.piclyt.utils.createBottomNavigation
import com.google.firebase.auth.FirebaseAuth

// ########################## Ecran d'accueil ######################### //
@Composable
fun MediaScreen(navController: NavController, context: Context, auth: FirebaseAuth, modifier: Modifier = Modifier) {
    createBottomNavigation(navController, context, modifier, true) // Affichage de la navigation

    Column {
        MediaHeaderText()
    }
}

@Composable
fun MediaHeaderText(){
    Text (
        text = "Média",
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp, bottom = 50.dp),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 50.sp,
        fontFamily = FontFamily.Serif,
        color = Color.Gray
    )
}

// ########################## Preview de l'ecran d'accueil ################ //
@Preview(showBackground = true)
@Composable
fun MediaScreenPreview() {
    PicLytTheme {
        MediaScreen(navController = rememberNavController(), LocalContext.current, FirebaseAuth.getInstance())
    }
}