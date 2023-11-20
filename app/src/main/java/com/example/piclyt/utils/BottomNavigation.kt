package com.example.piclyt.utils

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController

// ########################## Barre de navigation de l'application ######################### //
// Fonction de création du bottom Navigation
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun createBottomNavigation(navController: NavController, context: Context,  modifier: Modifier = Modifier, isSelected:Boolean) {

    // Liste des éléments de la navigation
    val items = listOf(
        BottomNavigationItem (
            title = "Profile",
            sublist = listOf("Settings"),
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            hasNews = true,
        ),
        BottomNavigationItem (
            title = "Media",
            sublist = listOf(""),
            selectedIcon = Icons.Filled.Create,
            unselectedIcon = Icons.Outlined.Create,
            hasNews = false,
            badgeCount = 5
        ),
        BottomNavigationItem (
            title = "Add",
            sublist = listOf(""),
            selectedIcon = Icons.Filled.Add,
            unselectedIcon = Icons.Outlined.Add,
            hasNews = false,
        ),
        BottomNavigationItem (
            title = "Home",
            sublist = listOf(""),
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false,
        ),
        BottomNavigationItem (
            title = "Shop",
            sublist = listOf(""),
            selectedIcon = Icons.Filled.ShoppingCart,
            unselectedIcon = Icons.Outlined.ShoppingCart,
            hasNews = true,
        )
    )

    val currentPosition = navController.currentDestination?.route.toString() // Permet de récupérer le nom de la page où on se trouve
    // Création de la barre de navigation
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold (
            bottomBar = {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = currentPosition == item.title || item.sublist.contains(currentPosition), // Active le logo correspondant au nom de la page
                            onClick = {
                                navController.navigate(item.title) // Navigation vers la page du même nom que l'élément
                                Toast.makeText(context, "Chargement de "+ item.title, Toast.LENGTH_SHORT).show()
                            },
                            label = {
                                Text(text = item.title) // Affichage du nom de l'élément
                            },
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (item.badgeCount != null) // Test de l'affichage du nombre dans la bulle possible ou pas
                                            Badge { Text(text = item.badgeCount.toString()) }
                                        else if (item.hasNews)
                                            Badge {}
                                    }
                                ) {
                                    Icon( // Affichage de l'icône de l'élément
                                        imageVector = if (isSelected) item.selectedIcon
                                        else item.unselectedIcon,
                                        contentDescription = item.title
                                    )
                                }
                            })
                    }
                }
            }
        ){ /* Vide  */ }
    }
}

// Liste des paramètres d'une fonction
data class BottomNavigationItem (
    val title: String,
    val sublist: List<String>, // Sous liste des éléments d'une section
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)



