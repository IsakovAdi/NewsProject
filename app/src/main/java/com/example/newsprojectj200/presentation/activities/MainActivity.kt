package com.example.newsprojectj200.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newsprojectj200.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}