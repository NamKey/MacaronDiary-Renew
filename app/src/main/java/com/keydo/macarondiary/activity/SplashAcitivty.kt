package com.keydo.macarondiary.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.keydo.macarondiary.R

class SplashAcitivty : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //Animation Splash
//        val mainImagesplash = findViewById<ImageView>(R.id.splash_imageView)

        //No Animation splash
        Handler().postDelayed(Runnable {
            val intentGomain = Intent(this,
                MainActivity::class.java)
            startActivity(intentGomain)
            finish()
        },10)


    }
}