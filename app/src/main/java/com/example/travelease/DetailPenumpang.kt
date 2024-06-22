package com.example.travelease

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailPenumpang : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_penumpang)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val itemsJenisIdentitas = listOf("KTP","SIM","Passport")
        val itemsTipePenumpang = listOf("Dewasa","Anak")
        val autoCompleteJenisIdentitas : AutoCompleteTextView = findViewById(R.id.auto_complete_jenis_identitas)
        val autoCompleteTipePenumpang : AutoCompleteTextView = findViewById(R.id.auto_complete_tipe_penumpang)
        val adapterJenisIdentitas = ArrayAdapter(this, R.layout.list_item, itemsJenisIdentitas)
        val adapterTipePenumpang = ArrayAdapter(this, R.layout.list_item, itemsTipePenumpang)

        autoCompleteJenisIdentitas.setAdapter(adapterJenisIdentitas)

        autoCompleteJenisIdentitas.onItemClickListener = AdapterView.OnItemClickListener {
                adapterView, view, i, l ->

            val selectedItem = adapterView.getItemAtPosition(i)
            Toast.makeText(this, "Item : $selectedItem", Toast.LENGTH_SHORT).show()
        }

        autoCompleteTipePenumpang.setAdapter(adapterTipePenumpang)

        autoCompleteTipePenumpang.onItemClickListener = AdapterView.OnItemClickListener {
                adapterView, view, i, l ->

            val selectedItem = adapterView.getItemAtPosition(i)
            Toast.makeText(this, "Item : $selectedItem", Toast.LENGTH_SHORT).show()
        }

        // Set the title with center alignment, 24dp size, and bold
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