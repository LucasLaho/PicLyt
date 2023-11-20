package com.example.piclyt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
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
import com.example.piclyt.ui.theme.PicLytTheme

data class UserProfile(
    val username: String,
    val albumCount: Int,
    val sharedAlbumCount: Int,
    val receivedAlbumCount: Int,
    val profileImage: Int // Replace with your actual image resource or URL
)

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PicLytTheme {
                UserProfilePage(
                    userProfile = UserProfile(
                        username = "jjiji",
                        albumCount = 3,
                        sharedAlbumCount = 5,
                        receivedAlbumCount = 2,
                        profileImage = R.drawable.ic_launcher_foreground
                    )
                )
            }
        }
    }
}

@Composable
fun UserProfilePage(userProfile: UserProfile) {
    var isEditing by remember { mutableStateOf(false) }

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                ProfilImage(
                    painter = painterResource(id = userProfile.profileImage),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(300.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = userProfile.username,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            ProfileStats(userProfile = userProfile)
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            EditProfileButton(onClick = { isEditing = true })
        }
    }
}


@Composable
fun ProfilImage(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
            .size(250.dp)  // Ici on peut augmenter ou diminuer la taille de l'image !
    )
}


@Composable
fun ProfileStats(userProfile: UserProfile) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.Gray)
            .padding(16.dp)
    ) {
        ProfileStatItem(label = "Nombre d'albums", value = userProfile.albumCount)
        ProfileStatItem(label = "Albums partagés", value = userProfile.sharedAlbumCount)
        ProfileStatItem(label = "Albums reçus", value = userProfile.receivedAlbumCount)
    }
}

@Composable
fun ProfileStatItem(label: String, value: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium, // Utilisez le style par défaut
            modifier = Modifier.weight(1f)
        )

        Text(
            text = value.toString(),
            style = LocalTextStyle.current
        )
    }
}


@Composable
fun EditProfileButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Éditer le profil")
    }
}

@Preview
@Composable
fun UserProfilePagePreview() {
    PicLytTheme {
        UserProfilePage(
            userProfile = UserProfile(
                username = "Jfdgfgde",
                albumCount = 3,
                sharedAlbumCount = 5,
                receivedAlbumCount = 2,
                profileImage = R.drawable.ic_launcher_foreground
            )
        )
    }
}