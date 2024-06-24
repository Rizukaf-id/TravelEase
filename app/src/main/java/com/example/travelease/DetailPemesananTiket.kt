package com.example.travelease

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.travelease.databinding.ActivityDetailPemesananTiketBinding
import com.example.travelease.databinding.ActivityPenumpangTersimpanBinding

class DetailPemesananTiket : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPemesananTiketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPemesananTiketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.linearLayout.setOnClickListener {
            val intent = Intent(this, DetailPenumpang::class.java)
            startActivity(intent)
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        val intent = Intent(this, PemesananKeretaLokal::class.java)
        startActivity(intent)
        super.onBackPressed()
    }
}