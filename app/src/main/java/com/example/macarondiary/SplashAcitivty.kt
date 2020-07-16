package com.example.macarondiary

import android.animation.*
import android.animation.Animator.AnimatorListener
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

class SplashAcitivty : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //Animation Splash
//        val mainImagesplash = findViewById<ImageView>(R.id.splash_imageView)

        //No Animation splash
        Handler().postDelayed(Runnable {
            val intentGomain = Intent(this,MainActivity::class.java)
            startActivity(intentGomain)
            finish()
        },1000)


    }
}