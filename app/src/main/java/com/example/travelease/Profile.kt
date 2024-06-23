package com.example.travelease

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth

class Profile : Fragment() {

    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = com.google.firebase.ktx.Firebase.auth

        auth = FirebaseAuth.getInstance()

        view.findViewById<LinearLayout>(R.id.daftarPenumpangButton).setOnClickListener {
            navigateToPenumpangTersimpan()
        }
        // Set OnClickListener for logout
        view.findViewById<LinearLayout>(R.id.logoutButton).setOnClickListener {
            Firebase.auth.signOut()
            logoutUser()
        }
    }

    private fun navigateToPenumpangTersimpan() {
        val intent = Intent(activity, PenumpangTersimpan::class.java)
        startActivity(intent)
    }

    private fun logoutUser() {
        // Clear user data and navigate to login screen
        // You can add your own logout logic here, like clearing shared preferences

        val intent = Intent(activity, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}