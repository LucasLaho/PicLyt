package com.example.piclyt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.piclyt.ui.theme.PicLytTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, true)

        setContent {
            PicLytTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

/* Fonctionnement global de l'application */
@Composable
fun MyApp(modifier: Modifier = Modifier) {
    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF87CEEB)) // Changement de couleur de l'application
        )
        Connection()
    }
}

/* Preview de l'application */
@Preview
@Composable
fun MyAppPreview() {
    PicLytTheme {
        MyApp(Modifier.fillMaxSize())
    }
}

/* Ecran de connexion */
@Composable
fun Connection() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        var usernameText by remember { mutableStateOf("") }
        var passwordText by remember { mutableStateOf("") }

        createLogo(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),// Logo de base de Android Studio. Pour choisir mes images à moi je dois utiliser accompanist coil pour importer à partir de URL
            contentDescription = "Logo"
        )

        CreateTextField(
            label = "Nom d'utilisateur",
            value = usernameText,
            onValueChange = { passwordText = it },
            backgroundColor = Color.White // Je choisis la couleur blanche. Pas bien optimisée !!
        )

        CreateTextField(
            label = "Mot de passe",
            value = passwordText,
            onValueChange = { passwordText = it },
            backgroundColor = Color.White // Je choisis la couleur blanche. Pas bien optimisée !!
        )

        Button(onClick = { /* Action de Connexion à dev ^^ */ }, modifier = Modifier.padding(top = 8.dp)) {
            Text("Connexion")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { /* Action d'inscription à dev  */ }) {
            Text("Inscription")
        }
    }
}

/* Preview de l'ecran de connexion */
@Preview(showBackground = true)
@Composable
fun ConnectionPreview() {
    PicLytTheme {
        Connection()
    }
}

// Création de TextField
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTextField(label: String, value: String, onValueChange: (String) -> Unit, backgroundColor: Color) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
            .background(backgroundColor)) // Ici je peux redefinir le fond d'écran des input text
}

// Création de Logo : A modifier car ne permet pas encore d'import d'image via URL
@Composable
fun createLogo(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
            .size(300.dp)  // Ici on peut augmenter ou diminuer la taille de l'image !
    )
}