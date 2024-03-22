package com.test.tnpconnect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.test.tnpconnect.AuthenticationCodes.AuthenticateUsingPhone
import com.test.tnpconnect.Util.AndroidUtil
import com.test.tnpconnect.databinding.ActivityOtpverificationBinding

class OTPVerificationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOtpverificationBinding
    private lateinit var phone : String
    private lateinit var enteredOTP : EditText
    private lateinit var pb : ProgressBar
    private lateinit var btnVerify : MaterialButton
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
            val otp = enteredOTP.text.toString()

            if (otp.length == 6)
            AuthenticateUsingPhone.verifyOTP(otp, this, pb, btnVerify)
            else enteredOTP.setError("OTP should be of 6 digits")
        }

        resendOTP.setOnClickListener {
            AuthenticateUsingPhone.sendOTP(phone, true, pb, btnVerify, this)
        }
    }
}