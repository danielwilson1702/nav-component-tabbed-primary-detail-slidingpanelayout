package com.myapplication.launch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import com.myapplication.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySplashBinding = ActivitySplashBinding.inflate(
            layoutInflater
        )
        setContentView(binding.root)
    }

}
