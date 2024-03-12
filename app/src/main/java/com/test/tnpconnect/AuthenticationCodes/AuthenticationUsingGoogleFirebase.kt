package com.test.tnpconnect.AuthenticationCodes

import android.content.Intent
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.test.tnpconnect.MainActivity
import com.test.tnpconnect.R
import com.test.tnpconnect.SignUpActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView

import com.google.firebase.firestore.FirebaseFirestore
import com.test.tnpconnect.AuthenticationCodes.AuthenticationUsingGoogleFirebase

class AuthenticationUsingGoogleFirebase {

//    companion object {
//        private const val RC_SIGN_IN = 9001
//    }
//
//   // private lateinit var auth: FirebaseAuth
//
//     var auth = FirebaseAuth.getInstance()
//
//    private fun signIn() {
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//
//        val googleSignInClient = GoogleSignIn.getClient(this, gso)
//        val signInIntent = googleSignInClient.signInIntent
//        startActivityForResult(signInIntent, SignUpActivity.RC_SIGN_IN)
//    }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == SignUpActivity.RC_SIGN_IN) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            try {
//                val account = task.getResult(ApiException::class.java)
//                firebaseAuthWithGoogle(account.idToken!!)
//            } catch (e: ApiException) {
//                Toast.makeText(this, "Google sign in failed: ${e.message}", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    private fun firebaseAuthWithGoogle(idToken: String) {
//        val credential = GoogleAuthProvider.getCredential(idToken, null)
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    val user = auth.currentUser
//                    Toast.makeText(this, "Signed in as ${user?.displayName}", Toast.LENGTH_SHORT).show()
//                    startActivity(Intent(this, MainActivity::class.java))
//                    finish()
//                } else {
//                    Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
//                }
//            }
//    }
}