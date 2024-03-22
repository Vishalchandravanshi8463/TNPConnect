package com.test.tnpconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import com.google.android.material.button.MaterialButton
import com.google.firebase.Timestamp
import com.test.tnpconnect.Model.UserModel
import com.test.tnpconnect.Util.AndroidUtil
import com.test.tnpconnect.Util.FirebaseUtil
import com.test.tnpconnect.databinding.ActivityNameUpdationBinding

class NameUpdation : AppCompatActivity() {
    private lateinit var binding : ActivityNameUpdationBinding
    private lateinit var phone : String
    private lateinit var edtName : EditText
    private lateinit var update : MaterialButton
    private lateinit var pb : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNameUpdationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        phone = intent.getStringExtra("number").toString()
        AndroidUtil.printMessage(this, phone)

        pb = binding.progressBar
        edtName = binding.edtNamee
        update = binding.btnRegister

        alreadyExists()
        Log.e("after already exists", "passed")

        update.setOnClickListener {
            setProgressBar(true)

            val name = edtName.text.toString()
            if(name.length > 2) {
                setName(name)
            } else {
                edtName.setError("Name length should be greater than 2 words!!")
                setProgressBar(false)
            }
        }
    }

    private fun setName(name: String) {
        val user = UserModel(
            FirebaseUtil.getCurrentUserId(),
            phone,
            name,
            Timestamp.now(),
            ""
        )

        FirebaseUtil.currentUserDetails().set(user!!).addOnCompleteListener { task ->
            if(task.isSuccessful) {
                AndroidUtil.printMessage(this, "Logged in successfully ${name}")
                val intent = Intent(this, MainActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun alreadyExists() {
        setProgressBar(true)

        FirebaseUtil.currentUserDetails().get().addOnCompleteListener { task ->
            if(task.isSuccessful) {
                val user = task.getResult().toObject(UserModel::class.java)
                Log.e("task successful", "${user}")

                if(user == null) {
                    setProgressBar(false)
                    return@addOnCompleteListener
                }else{
                    val name = user.userName
                    AndroidUtil.printMessage(this, "Logged in successfully ${name}")

                    val intent = Intent(this, MainActivity::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun setProgressBar(flag : Boolean) {
        if(flag) {
            pb.visibility = View.VISIBLE
            update.visibility = View.GONE
        } else {
            pb.visibility = View.GONE
            update.visibility = View.VISIBLE
        }
    }
}