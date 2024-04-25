package com.test.tnpconnect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        val bottomView=findViewById<BottomNavigationView>(R.id.bottomNavigation)
        replaceWithFragement(Home());

        bottomView.setOnItemSelectedListener{
            when(it.itemId) {
                R.id.home-> replaceWithFragement(Home())
                R.id.profile-> replaceWithFragement(Profile())
                else->{

                }
            }
            true
        }
    }
    private fun replaceWithFragement(fragment : Fragment) {
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.FrameLayout,fragment)
        fragmentTransaction.commit()
    }
}

