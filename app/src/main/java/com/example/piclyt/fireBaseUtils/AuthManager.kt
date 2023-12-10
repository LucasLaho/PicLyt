package com.example.piclyt.fireBaseUtils

import android.content.Intent
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.example.piclyt.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class AuthManager(private val activity: ComponentActivity) {

    private val RC_SIGN_IN = 100
    private var googleSignInClient: GoogleSignInClient
    private var auth: FirebaseAuth

    init {
        // Configure Google SignIn and FirebaseAuth here
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(activity.getString(R.string.web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(activity, gso)
        auth = FirebaseAuth.getInstance()
    }

    val getAuth: FirebaseAuth
        get() = FirebaseAuth.getInstance()

    fun signIn() : Boolean {
        var connected = false
        val signInIntent = googleSignInClient.signInIntent
        activity.activityResultRegistry.register("GoogleSignInResult", ActivityResultContracts.StartActivityForResult()) { result ->
            val data: Intent? = result.data
            if(handleSignInResult(data))
                connected = true
        }
        activity.startActivityForResult(signInIntent, RC_SIGN_IN)

        return connected
    }

    private fun handleSignInResult(data: Intent?) : Boolean{
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)

        if (task.isSuccessful) {
            try {
                val account = task.getResult(ApiException::class.java)!!
                if(firebaseAuthWithGoogle(account.idToken!!))
                    return true
            } catch (e: Exception) {
                Toast.makeText(activity, "Google SignIn Failed", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(activity, "Google SignIn Result = Failed", Toast.LENGTH_SHORT).show()
        }
        return false
    }
    private fun firebaseAuthWithGoogle(idToken: String) : Boolean {
        var success = false
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(activity, "SignIn Successful", Toast.LENGTH_SHORT).show()
                    success = true
                } else {
                    Toast.makeText(activity, "SignIn Failed", Toast.LENGTH_SHORT).show()
                }
            }
        return success
    }

    fun signOut() {
        // configure Google SignIn
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(activity.getString(R.string.web_client_id))
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(activity, gso)

        // Sign Out of all accounts
        auth.signOut()
        googleSignInClient.signOut()
            .addOnSuccessListener {
                Toast.makeText(activity, "Sign Out Successful", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(activity, "Sign Out Failed", Toast.LENGTH_SHORT).show()
            }
    }
}