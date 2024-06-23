package com.example.travelease

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.travelease.databinding.ActivityRegisterBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        auth = FirebaseAuth.getInstance()

        binding.daftar.setOnClickListener {
            val email = binding.inputEmail.text.toString()
            val password = binding.inputConfirmpassword.text.toString()
            val confirmPassword = binding.inputConfirmpassword.text.toString()
            val confirmbutton = binding.confirm

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty() && confirmbutton.isChecked){
                if (password == confirmPassword){
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                        if (it.isSuccessful){
                            Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                        }
                        else {
                            Toast.makeText(this, it.result.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this, "Password dan Confirm Password tidak sama", Toast.LENGTH_SHORT).show()
                }
            }  else{
                Toast.makeText(this, "Email dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }

        binding.textViewLogin.setOnClickListener {
            login()
        }
    }

    private fun login(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}