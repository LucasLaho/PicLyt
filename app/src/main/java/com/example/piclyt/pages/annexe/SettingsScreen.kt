package com.example.piclyt.pages.annexe

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.piclyt.R
import com.example.piclyt.ui.theme.PicLytTheme
import com.example.piclyt.utils.createBottomNavigation
import com.google.firebase.auth.FirebaseAuth

// ########################## Ecran de paramètres ######################### //
@Composable
fun SettingsScreen(navController: NavController, context: Context, auth: FirebaseAuth, modifier: Modifier = Modifier) {
    createBottomNavigation(navController, context, modifier, true) // Affichage de la navigation

    Column {
        SettingsHeaderText()
        ProfileCard()
    }
}
@Composable
fun SettingsHeaderText(){
    Text (
        text = "Settings",
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 10.dp),
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp,
        fontFamily = FontFamily.Serif,
        color = Color.Gray
    )
}

@Composable
fun ProfileCard(){
    Card (modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)
        .padding(10.dp)){

            Row (
                modifier = Modifier.padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column {
                    Text(text = "Check your profile",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        color = Color.Gray
                   )
                }
                Image(painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "",
                    modifier = Modifier.height(120.dp))
            }
    }
}

// ########################## Preview de l'ecran de paramètres ################ //
@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    PicLytTheme {
        SettingsScreen(navController = rememberNavController(), LocalContext.current, FirebaseAuth.getInstance())
    }
}