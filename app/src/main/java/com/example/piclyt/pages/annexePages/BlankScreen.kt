package com.example.piclyt.pages.annexePages

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// ########################## Ecran vide ######################### //

// Fonction principale de la page vide
@Composable
fun BlankScreen(modifier: Modifier) {
    Surface(modifier, color = MaterialTheme.colorScheme.background) {
    }
}