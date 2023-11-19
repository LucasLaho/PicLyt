package com.example.piclyt.homepage

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.piclyt.ui.theme.PicLytTheme
import com.google.firebase.auth.FirebaseAuth

/* Ecran d'accueil */
@Composable
fun HomePageScreen(navController: NavController, context: Context, auth: FirebaseAuth, modifier: Modifier = Modifier) {
    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        Button(
            onClick = { Deconnection(navController, context, auth) },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Déconnexion")
        }
    }
}

/* Preview de l'ecran d'accueil */
@Preview(showBackground = true)
@Composable
fun ConnectionPreview() {
    PicLytTheme {
        HomePageScreen(navController = rememberNavController(), LocalContext.current, FirebaseAuth.getInstance())
    }
}