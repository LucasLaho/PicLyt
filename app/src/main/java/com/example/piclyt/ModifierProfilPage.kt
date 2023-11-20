package com.example.piclyt

// ModifierProfilePage.kt
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.piclyt.ui.theme.PicLytTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModifierProfilePage(onBackPressed: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        TopAppBar(
            title = { Text("Modifier le profil") },
            navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ModifierProfileItem(label = "Adresse e-mail", value = "utilisateur@email.com")
        ModifierProfileItem(label = "Nom d'utilisateur", value = "NomUtilisateur")
        // Ajoutez d'autres éléments de modification du profil selon vos besoins
    }
}

@Composable
fun ModifierProfileItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = label, style = MaterialTheme.typography.bodyLarge)
            Text(text = value, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview
@Composable
fun ModifierProfilePagePreview() {
    PicLytTheme {
        ModifierProfilePage(onBackPressed = { /* Handle back press */ })
    }
}
