package com.example.tnpconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SignUpActivity : AppCompatActivity() {

    lateinit var btnSignUp:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        btnSignUp=findViewById(R.id.btnSignUpWithMobileNumber)

        btnSignUp.setOnClickListener {
            startActivity(Intent(this,SignUpPhoneNumberActivity::class.java))
        }

    }
}