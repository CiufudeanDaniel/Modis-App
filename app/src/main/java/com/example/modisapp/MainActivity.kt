package com.example.modisapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.modisapp.view_models.PhotoViewModel

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val viewModel = PhotoViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.photos.observe(this) { photos ->
            Log.v(TAG, photos.toString())
        }
    }
}