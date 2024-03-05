package com.example.testtask

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var quoteTextView: TextView

    companion object {
        private const val SETTINGS_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE)

        quoteTextView = findViewById(R.id.quoteTextView)
        quoteTextView.text = sharedPreferences.getString("quote", "Цитата дня")

        val GoToVideo: Button = findViewById(R.id.videosButton)
        GoToVideo.setOnClickListener {
            val intent = Intent(this, VideoActivity::class.java)
            startActivity(intent)
        }

        val GoToImage: Button = findViewById(R.id.imagesButton)
        GoToImage.setOnClickListener {
            val intent = Intent(this, ImageActivity::class.java)
            startActivity(intent)
        }

        val GoToSettings: Button = findViewById(R.id.settingsButton)
        GoToSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivityForResult(intent, SETTINGS_REQUEST_CODE)
        }

        val GoToThereD: Button = findViewById(R.id.objects3DButton)
        GoToThereD.setOnClickListener {
            val intent = Intent(this, ThereD_Activity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SETTINGS_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val updatedQuote = data?.getStringExtra("updatedQuote")
            if (!updatedQuote.isNullOrBlank()) {
                quoteTextView.text = updatedQuote
            }
        }
    }
}
