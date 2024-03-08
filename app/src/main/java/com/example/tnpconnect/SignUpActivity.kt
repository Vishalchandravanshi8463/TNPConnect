package com.example.tnpconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class SignUpActivity : AppCompatActivity() {

    lateinit var btnSignUp:TextView
    lateinit var btnGoogleSignUp:LinearLayout
    lateinit var btnSignInn:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        btnSignUp=findViewById(R.id.btnSignUpWithMobileNumber)

        btnSignUp.setOnClickListener {
            startActivity(Intent(this,SignUpPhoneNumberActivity::class.java))
        }

        btnGoogleSignUp=findViewById(R.id.googleSignUp)

        btnGoogleSignUp.setOnClickListener {

            Toast.makeText(this, "Google Button Click", Toast.LENGTH_SHORT).show()
        }

        btnSignInn=findViewById(R.id.btnSignIn)

        btnSignInn.setOnClickListener {
            startActivity(Intent(this,SignIn::class.java))
        }

        fun show() {

        }
    }
}