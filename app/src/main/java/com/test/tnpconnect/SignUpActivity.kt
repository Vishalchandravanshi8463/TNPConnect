package com.test.tnpconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.test.tnpconnect.AuthenticationCodes.AuthenticationUsingGoogleFirebase
import com.test.tnpconnect.databinding.ActivitySignUpBinding

open class SignUpActivity : AppCompatActivity() {
    companion object {
        private const val RC_SIGN_IN = 9001
    }

    private lateinit var binding : ActivitySignUpBinding
    private lateinit var authHelper : AuthenticationUsingGoogleFirebase
    private lateinit var btnSignUp:TextView
    private lateinit var btnGoogleSignUp: MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authHelper = AuthenticationUsingGoogleFirebase(this)
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnSignUp = binding.btnSignUpWithMobileNumber
        btnSignUp.setOnClickListener {
            startActivity(Intent(this,SignUpPhoneNumberActivity::class.java))
        }

        btnGoogleSignUp = binding.btnSignUpWithGoogle

        //from here all code of signup with google
        btnGoogleSignUp.setOnClickListener {
            Toast.makeText(this, "Google Button Clicked..", Toast.LENGTH_SHORT).show()
            authHelper.signInWithGoogle(RC_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RC_SIGN_IN) {
            authHelper.handleSignInResult(data, this)
        }
    }
}
