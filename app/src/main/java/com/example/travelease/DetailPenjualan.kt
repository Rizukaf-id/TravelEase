package com.example.travelease

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class DetailPenjualan : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_penjualan)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val itemsBulan = listOf("Januari","Februari","Maret","April","Mei","Juni","Juli","Agustus","September","Oktober","November","Desember")
        val itemsTahun = listOf("2021","2022","2023","2024")
        val autoCompleteBulan : AutoCompleteTextView = findViewById(R.id.auto_complete_bulan)
        val autoCompleteTahun : AutoCompleteTextView = findViewById(R.id.auto_complete_tahun)
        val adapterBulan = ArrayAdapter(this, R.layout.list_item, itemsBulan)
        val adapterTahun = ArrayAdapter(this, R.layout.list_item, itemsTahun)
        val button: Button = findViewById(R.id.button)

        autoCompleteBulan.setAdapter(adapterBulan)

        autoCompleteBulan.onItemClickListener = AdapterView.OnItemClickListener {
                adapterView, view, i, l ->

            val selectedItem = adapterView.getItemAtPosition(i)
            Toast.makeText(this, "Item : $selectedItem", Toast.LENGTH_SHORT).show()
        }

        autoCompleteTahun.setAdapter(adapterTahun)

        autoCompleteTahun.onItemClickListener = AdapterView.OnItemClickListener {
                adapterView, view, i, l ->

            val selectedItem = adapterView.getItemAtPosition(i)
            Toast.makeText(this, "Item : $selectedItem", Toast.LENGTH_SHORT).show()
        }

        val onItemSelectedListener = AdapterView.OnItemClickListener { _, _, _, _ ->
            button.isEnabled = true
        }

        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false) // Disable default title
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val toolbarTitle: TextView = findViewById(R.id.toolbar_title)
        toolbarTitle.text = "Detail Penjualan"
        toolbarTitle.gravity = Gravity.CENTER

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        val intent = Intent(this, InventarisView::class.java)
        startActivity(intent)
        super.onBackPressed()
    }

}