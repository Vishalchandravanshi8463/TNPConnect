package com.test.tnpconnect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.tnpconnect.Util.AndroidUtil
import com.test.tnpconnect.databinding.ActivityNameUpdationBinding

class NameUpdation : AppCompatActivity() {
    private lateinit var binding : ActivityNameUpdationBinding
    private lateinit var phone : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNameUpdationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        phone = intent.getStringExtra("number").toString()
        AndroidUtil.printMessage(this, phone)
    }
}