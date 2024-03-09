package com.test.tnpconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import com.hbb20.CountryCodePicker
import com.test.tnpconnect.databinding.ActivitySignUpPhoneNumberBinding

class SignUpPhoneNumberActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySignUpPhoneNumberBinding
    private lateinit var countryCodePicker : CountryCodePicker
    private lateinit var number : EditText
    private lateinit var btnSendOTP : Button
    private lateinit var pb : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpPhoneNumberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        countryCodePicker = binding.countryCode
        number = binding.phoneNumber
        btnSendOTP = binding.btnSend
        pb = binding.progressBar

        pb.visibility = View.GONE

        countryCodePicker.registerCarrierNumberEditText(number)

        btnSendOTP.setOnClickListener {
            if(!countryCodePicker.isValidFullNumber) {
                number.setError("Number should be correct!!")
                return@setOnClickListener
            }

            val intent = Intent(this, OTPVerificationActivity::class.java)
            intent.putExtra("number", countryCodePicker.fullNumberWithPlus)
            startActivity(intent)
        }
    }
}