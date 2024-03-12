package com.test.tnpconnect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.test.tnpconnect.AuthenticationCodes.AuthenticateUsingPhone
import com.test.tnpconnect.Util.AndroidUtil
import com.test.tnpconnect.databinding.ActivityOtpverificationBinding

class OTPVerificationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOtpverificationBinding
    private lateinit var phone : String
    private lateinit var enteredOTP : EditText
    private lateinit var pb : ProgressBar
    private lateinit var btnVerify : Button
    private lateinit var resendOTP:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpverificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enteredOTP = binding.edtOtp
        pb = binding.progressBar
        btnVerify = binding.verifyOTP
        resendOTP=binding.resendOTP

        phone = intent.extras?.getString("number").toString()
        // AndroidUtil.printMessage(this, phone)

        AuthenticateUsingPhone.sendOTP(phone, false, pb, btnVerify, this)

        btnVerify.setOnClickListener {
            AuthenticateUsingPhone.verifyOTP(enteredOTP.text.toString(), this, pb, btnVerify)
        }

        resendOTP.setOnClickListener {
            AuthenticateUsingPhone.sendOTP(phone, true, pb, btnVerify, this)
        }
    }
}