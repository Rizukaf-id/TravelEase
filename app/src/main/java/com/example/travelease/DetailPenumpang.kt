package com.example.travelease

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.travelease.databinding.ActivityDetailPenumpangBinding

class DetailPenumpang : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPenumpangBinding
    private lateinit var saveButton: Button
    private lateinit var identityNumberEditText: EditText
    private lateinit var fullNameEditText: EditText
    private lateinit var autoCompleteJenisIdentitas: AutoCompleteTextView
    private lateinit var autoCompleteTipePenumpang: AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_penumpang)
        binding = ActivityDetailPenumpangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val itemsJenisIdentitas = listOf("KTP", "SIM", "Passport")
        val itemsTipePenumpang = listOf("Dewasa", "Anak")
        identityNumberEditText = findViewById(R.id.identity_number_edit_text)
        fullNameEditText = findViewById(R.id.full_name_edit_text)
        autoCompleteJenisIdentitas = findViewById(R.id.auto_complete_jenis_identitas)
        autoCompleteTipePenumpang = findViewById(R.id.auto_complete_tipe_penumpang)
        val adapterJenisIdentitas = ArrayAdapter(this, R.layout.list_item, itemsJenisIdentitas)
        val adapterTipePenumpang = ArrayAdapter(this, R.layout.list_item, itemsTipePenumpang)
        saveButton = findViewById(R.id.save_button)

        autoCompleteJenisIdentitas.setAdapter(adapterJenisIdentitas)

        autoCompleteJenisIdentitas.onItemClickListener = AdapterView.OnItemClickListener { adapterView, _, i, _ ->
            val selectedItem = adapterView.getItemAtPosition(i)
            Toast.makeText(this, "Item : $selectedItem", Toast.LENGTH_SHORT).show()
            checkForChanges()
        }

        autoCompleteTipePenumpang.setAdapter(adapterTipePenumpang)

        autoCompleteTipePenumpang.onItemClickListener = AdapterView.OnItemClickListener { adapterView, _, i, _ ->
            val selectedItem = adapterView.getItemAtPosition(i)
            Toast.makeText(this, "Item : $selectedItem", Toast.LENGTH_SHORT).show()
            checkForChanges()
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

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkForChanges()
            }
            override fun afterTextChanged(s: Editable?) {}
        }

        identityNumberEditText.addTextChangedListener(textWatcher)
        fullNameEditText.addTextChangedListener(textWatcher)
    }

    private fun checkForChanges() {
        saveButton.isEnabled = autoCompleteJenisIdentitas.text.isNotBlank() ||
                identityNumberEditText.text.isNotBlank() ||
                fullNameEditText.text.isNotBlank() ||
                autoCompleteTipePenumpang.text.isNotBlank()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        val intent = Intent(this, PenumpangTersimpan::class.java)
        startActivity(intent)
        super.onBackPressed()
    }
}