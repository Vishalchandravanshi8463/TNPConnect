package com.test.tnpconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.test.tnpconnect.AuthenticationCodes.AppConstants
import com.test.tnpconnect.databinding.ActivityConferenceStartBinding
import com.zegocloud.uikit.prebuilt.videoconference.ZegoUIKitPrebuiltVideoConferenceConfig
import com.zegocloud.uikit.prebuilt.videoconference.ZegoUIKitPrebuiltVideoConferenceFragment

class conferenceStartActivity : AppCompatActivity() {
    private lateinit var binding : ActivityConferenceStartBinding
    private lateinit var meet_id : TextView
    private lateinit var share_icon : ImageView

    private lateinit var name : String
    private lateinit var meetingId : String
    private lateinit var userId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConferenceStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        share_icon = binding.shareImg
        meet_id = binding.meetingIdTextview

        val intent = intent
        name = intent.getStringExtra("name").toString()
        userId = intent.getStringExtra("user_id").toString()
        meetingId = intent.getStringExtra("id").toString()

        meet_id.setText("MEETING ID : " + meetingId)

        addFragment()

        share_icon.setOnClickListener {
            val intent = Intent()
            intent.setAction(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT, "Join Meeting on TNPConnect \n Meeting ID : ${meetingId}")
            startActivity(Intent.createChooser(intent, "Share via..."))
        }
    }

    fun addFragment() {
        val appID: Long = AppConstants.appId
        val appSign: String = AppConstants.appSign

        val config = ZegoUIKitPrebuiltVideoConferenceConfig()
        config.turnOnCameraWhenJoining = false
        config.turnOnMicrophoneWhenJoining = false
        val fragment = ZegoUIKitPrebuiltVideoConferenceFragment.newInstance(
            appID,
            appSign,
            userId,
            name,
            meetingId,
            config
        )
        supportFragmentManager.beginTransaction()
            .replace(R.id.fc, fragment)
            .commitNow()
    }
}