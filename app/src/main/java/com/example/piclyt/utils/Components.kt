package com.example.piclyt.utils

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.piclyt.R
import com.example.piclyt.data.MediaModel
import com.example.piclyt.fireBaseUtils.AuthManager

// Utilité : Ici, on retrouve toutes les fonctions de créations de composants pour toutes les pages de l'application !

// ########################## Fonctions de créations de composants pour l'application ######################### //

// Fonction pour la création d'un TextField
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTextField(label: String, onValueChange: (String) -> Unit) {
    var text by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(it)
        },
        singleLine = true,
        label = { Text(label) },
    )
}

// Fonction pour la création d'un TextField pour mot de passe
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePasswordTextField(label: String, onValueChange: (String) -> Unit) {
    var text by rememberSaveable { mutableStateOf("") }
    var showText by rememberSaveable { mutableStateOf(value = false) }
    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(it)
        },
        singleLine = true,
        label = { Text(label) },
        visualTransformation = if (showText) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon = {
            if (showText) {
                IconButton(onClick = { showText = false }) {
                    Icon(
                        imageVector = Icons.Filled.Visibility,
                        contentDescription = "hide_text"
                    )
                }
            } else {
                IconButton(onClick = { showText = true }) {
                    Icon(
                        imageVector = Icons.Filled.VisibilityOff,
                        contentDescription = "hide_text"
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    )
}

// Fonction pour la création de Logo
@Composable
fun CreateLogo(painter: Painter, contentDescription: String?, modifier: Modifier = Modifier) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
            .size(300.dp)  // Ici on peut augmenter ou diminuer la taille de l'image !
    )
}

// Fonction pour la création de titre pour une page
@Composable
fun createHeaderText(text: String){
    Text (
        text = text,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        fontSize = 25.sp,
        fontWeight = FontWeight.ExtraBold,
    )
}

// ########################## Barre de navigation de l'application ######################### //

// Liste des paramètres de la fonction : "createBottomNavigation"
data class BottomNavigationItem (
    val title: String,
    val routeName: String,
    val sublist: List<String>, // Sous liste des éléments d'une section
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

// Fonction de création du bottom Navigation
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun createBottomNavigation(navController: NavController, context: Context, modifier: Modifier = Modifier, isSelected:Boolean, viewModel: MediaModel) {

    // Liste des éléments de la navigation
    val items = listOf(
        BottomNavigationItem (
            title = "Accueil",
            routeName = "Home",
            sublist = listOf(""),
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false,
        ),
        BottomNavigationItem (
            title = "Amis",
            routeName = "Friends",
            sublist = listOf(""),
            selectedIcon = Icons.Filled.Group,
            unselectedIcon = Icons.Outlined.Group,
            hasNews = false,
            //badgeCount = 0
        ),
        BottomNavigationItem (
            title = "Ajouter",
            routeName = "Add",
            sublist = listOf(""),
            selectedIcon = Icons.Filled.Add,
            unselectedIcon = Icons.Outlined.Add,
            hasNews = false,
        ),
        BottomNavigationItem (
            title = "Boutique",
            routeName = "Shop",
            sublist = listOf(""),
            selectedIcon = Icons.Filled.ShoppingCart,
            unselectedIcon = Icons.Outlined.ShoppingCart,
            hasNews = false,
        ),
        BottomNavigationItem (
            title = "Profil",
            routeName = "Profile",
            sublist = listOf("Settings"),
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            hasNews = false,
        )
    )

    val currentPosition = navController.currentDestination?.route.toString() // Permet de récupérer le nom de la page où on se trouve

    ///////////// Création de la barre de navigation ///////////////
    Surface {
        Scaffold (
            bottomBar = {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = currentPosition == item.routeName || item.sublist.contains(currentPosition), // Active le logo correspondant au nom de la page
                            onClick = {
                                navController.navigate(item.routeName) // Navigation vers la page du même nom que l'élément
                            },
                            label = {
                                Text(text = item.title) // Affichage du nom de l'élément
                            },
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (item.badgeCount != null) // Test de l'affichage du nombre dans la bulle possible ou pas
                                            Badge {
                                                Text(text = viewModel.selectedImageUris.size.toString())
                                            }
                                        else if (item.hasNews)
                                            Badge {}
                                    }
                                ) {
                                    Icon( // Affichage de l'icône de l'élément
                                        imageVector = if (isSelected) item.selectedIcon
                                        else item.unselectedIcon,
                                        contentDescription = item.routeName
                                    )
                                }
                            })
                    }
                }
            }
        ){ /* Vide  */ }
    }
}

@Composable
fun createGoogleSignInButton(
    context: Context,
    authManager: AuthManager,
    navController: NavController
) {
    Button(
        onClick = {
            if (authManager.signIn()) {
                navController.navigate("Home")
                Toast.makeText(context, "Bouton appuyé", Toast.LENGTH_SHORT).show()
            }
        },
        modifier = Modifier
            .height(55.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(width = 1.5.dp, color = Color.Black),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .padding(start = 2.dp)
                    .size(32.dp)
                    .padding(0.dp)
                    .align(Alignment.CenterVertically),
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = "google_logo"
            )
            Text(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .align(Alignment.CenterVertically),
                text = "Connexion avec Google",
                fontSize = 15.sp,
                color = Color.Black
            )
        }
    }
}