package com.example.travelease

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.travelease.databinding.ActivityPemesananKeretaLokalBinding

class PemesananKeretaLokal : AppCompatActivity() {
    private lateinit var binding: ActivityPemesananKeretaLokalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPemesananKeretaLokalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cariKereta.setOnClickListener {
            val intent = Intent(this, PilihKereta::class.java)
            startActivity(intent)
        }
    }
}