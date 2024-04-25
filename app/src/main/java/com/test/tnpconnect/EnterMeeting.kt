package com.test.tnpconnect

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.test.tnpconnect.Model.UserModel
import com.test.tnpconnect.Util.FirebaseUtil
import com.test.tnpconnect.databinding.ActivityEnterMeetingBinding
import java.util.*


class EnterMeeting : AppCompatActivity() {
    private lateinit var binding : ActivityEnterMeetingBinding
    private lateinit var id : TextInputEditText
    private lateinit var name : TextInputEditText
    private lateinit var startMeeting : MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterMeetingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val meetingId = intent.getStringExtra("id")

        id = binding.meetingIdInput
        name = binding.nameInput
        startMeeting = binding.startMeeting

        if(meetingId?.length == 10)
            id.setText(meetingId)

        FirebaseUtil.currentUserDetails().get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document.exists()) {
                    val currentUserModel = document.toObject(UserModel::class.java)!!
                    name.setText(currentUserModel.userName)
                }
            }
        }

        startMeeting.setOnClickListener {
            if(name.length() < 3) {
                name.setError("Name is required to join the meeting")
                name.requestFocus()
                return@setOnClickListener
            }

            if(id.length() != 10) {
                id.setError("Meeting Id is of 10 digits...")
                id.requestFocus()
                return@setOnClickListener
            }

            startVideoConference(name.text.toString(), id.text.toString())
        }
    }

    private fun startVideoConference(name : String, meetId : String) {
        val userId = UUID.randomUUID().toString()

        val intent = Intent(this, conferenceStartActivity::class.java)
        intent.putExtra("name", name)
        intent.putExtra("id", meetId)
        intent.putExtra("user_id", userId)
        startActivity(intent)
    }
}