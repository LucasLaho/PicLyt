package com.example.piclyt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import com.example.piclyt.ui.theme.PicLytTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
fun Connection(modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        var usernameText by remember { mutableStateOf("") }
        var passwordText by remember { mutableStateOf("") }
        SimpleOutlinedTextFieldSample("Nom d'utilisateur", onValueChange = { usernameText = it })
        SimpleOutlinedTextFieldSample("Mot de passe", onValueChange = { passwordText = it })
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleOutlinedTextFieldSample(hint: String, onValueChange: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(it)
        },
        placeholder = { Text(hint) },
        singleLine = true
        )
}

@Preview(showBackground = true)
@Composable
fun SimpleOutlinedTextFieldSamplePreview() {
    PicLytTheme {
        SimpleOutlinedTextFieldSample("blblblb", onValueChange = {})
    }
}

/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PicLytTheme {
        Greeting("Android")
    }
}
*/