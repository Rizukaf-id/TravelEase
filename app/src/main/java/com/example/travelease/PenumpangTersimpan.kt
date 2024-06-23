package com.example.travelease

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.travelease.databinding.ActivityPenumpangTersimpanBinding

class PenumpangTersimpan : AppCompatActivity() {

    private lateinit var binding: ActivityPenumpangTersimpanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPenumpangTersimpanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // Define your list of passengers
        val penumpangList = listOf(
            Penumpang("Dewasa", "Joko Anwar"),
            Penumpang("Anak-Anak", "Joko Jr")
        )

        // Inflate and add each passenger view to the LinearLayout
        for (penumpang in penumpangList) {
            val passengerView = LayoutInflater.from(this).inflate(R.layout.item_penumpang, null)

            val passengerTypeTextView: TextView = passengerView.findViewById(R.id.passenger_type)
            val passengerNameTextView: TextView = passengerView.findViewById(R.id.passenger_name)

            passengerTypeTextView.text = penumpang.type
            passengerNameTextView.text = penumpang.name

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            val margin = resources.getDimensionPixelSize(R.dimen.item_penumpang_margin_bottom)
            layoutParams.setMargins(0, 0, 0, margin)
            passengerView.layoutParams = layoutParams

            // Add the view to the LinearLayout
            binding.linearLayout.addView(passengerView)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("navigateToProfile", true)
        startActivity(intent)
        super.onBackPressed()
    }

}