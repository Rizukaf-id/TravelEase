package com.example.travelease

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.travelease.databinding.ActivityPilihKeretaBinding
import com.example.travelease.databinding.ItemKeretaBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.io.Serializable

class PilihKereta : AppCompatActivity() {
    private lateinit var binding: ActivityPilihKeretaBinding
    private lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPilihKeretaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showTrain()

        binding.linearLayout.setOnClickListener(){
            val intent = Intent(this, DetailPemesananTiket::class.java)
            startActivity(intent)
        }
    }

    fun showTrain() {
        val db = FirebaseFirestore.getInstance()
        db.collection("tickets")
            .get()
            .addOnSuccessListener { result ->
                val trains = result.map { it.data }
                addTrainsToLinearLayout(trains)
            }
    }

    fun addTrainsToLinearLayout(trains: List<Map<String, Any>>) {
        val linearLayout = binding.linearLayout
        val inflater = LayoutInflater.from(this)

        for (train in trains) {
            val itemBinding = ItemKeretaBinding.inflate(inflater, linearLayout, false)
            itemBinding.kereta.text = train["armada_name"] as? String
            itemBinding.harga.text = "Rp " + (train["price"] as? String)
            itemBinding.jamBerangkat.text = train["departure_time"] as? String
            itemBinding.jamSampai.text = train["arrival_time"] as? String
            itemBinding.stasiunBerangkat.text = train["departure_location"] as? String
            itemBinding.stasiunSampai.text = train["arrival_location"] as? String
            itemBinding.root.setOnClickListener{
                val intent = Intent(this, DetailPemesananTiket::class.java)
                intent.putExtra("armada_name", train["armada_name"] as? String)
                intent.putExtra("price", train["price"] as? String)
                intent.putExtra("departure_time", train["departure_time"] as? String)
                intent.putExtra("arrival_time", train["arrival_time"] as? String)
                intent.putExtra("departure_location", train["departure_location"] as? String)
                intent.putExtra("arrival_location", train["arrival_location"] as? String)
                startActivity(intent)
            }
            linearLayout.addView(itemBinding.root)
        }
    }
}