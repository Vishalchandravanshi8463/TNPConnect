package com.test.tnpconnect.AuthenticationCodes

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.test.tnpconnect.MainActivity
import com.test.tnpconnect.R
import com.test.tnpconnect.SignUpActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.Timestamp
import com.test.tnpconnect.Model.UserModel
import com.test.tnpconnect.Util.AndroidUtil
import com.test.tnpconnect.Util.FirebaseUtil

class AuthenticationUsingGoogleFirebase(private val activity : Activity) {
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun signInWithGoogle(requestCode : Int) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(activity.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(activity, gso)
        val signInIntent = googleSignInClient.signInIntent
        activity.startActivityForResult(signInIntent, requestCode)
    }

    fun handleSignInResult(data: Intent?, context : Context) {
        val task = data?.let { Auth.GoogleSignInApi.getSignInResultFromIntent(it) }
        if (task != null && task.isSuccess) {
            val account = task.signInAccount
            firebaseAuthWithGoogle(account, context)
        } else {
            // Handle sign-in failure
            AndroidUtil.printMessage(activity, "Google Sign in failed!!")
        }
    }

    private fun firebaseAuthWithGoogle(google : GoogleSignInAccount?, context: Context) {
        val credential = GoogleAuthProvider.getCredential(google?.idToken, null)

        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    if(activity is SignUpActivity) {
                        val user = FirebaseAuth.getInstance().currentUser
                        AndroidUtil.printMessage(activity, "Authenticated as ${user?.displayName}")

                        FirebaseUtil.currentUserDetails().get().addOnCompleteListener { task ->
                            if(task.isSuccessful) {
                                var user = task.getResult().toObject(UserModel::class.java)

                                if(user == null) {
                                    user = UserModel(
                                        FirebaseUtil.getCurrentUserId(),
                                        "",
                                        google?.displayName,
                                        Timestamp.now(),
                                        google?.email
                                    )

                                    FirebaseUtil.currentUserDetails().set(user).addOnCompleteListener {
                                        AndroidUtil.printMessage(activity, "User added..")
                                    }
                                }
                            }
                        }

                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                        (context as Activity).finish()
                        return@addOnCompleteListener
                    }

                    AndroidUtil.printMessage(activity, "Signed in as ${google?.displayName}")
                } else {
                    AndroidUtil.printMessage(activity, "Authentication failed!!")
                }
            }
    }
}