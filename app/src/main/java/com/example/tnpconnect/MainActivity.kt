package com.example.tnpconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

  lateinit var  topAnim: Animation
    lateinit var bottomAnim:Animation;
    lateinit var image:ImageView
    lateinit var appNamee:TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)


//        topAnim=AnimationUtils.loadAnimation(this,R.anim.top_animation)
//        bottomAnim=AnimationUtils.loadAnimation(this,R.anim.bottom_animation)
//
//        image=findViewById(R.id.imageView)
//        appNamee=findViewById(R.id.appName)
//
//        image.setAnimation(topAnim)
//        appNamee.setAnimation(bottomAnim)
//
//
//        Handler(Looper.getMainLooper()).postDelayed({
//            startActivity(Intent(this,SignUpActivity::class.java))
//            finish()
//        },3000)

    }
}