package com.example.travelease

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
//import androidx.navigation.fragment.findNavController
import com.example.travelease.databinding.FragmentHomeBinding

class Home : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = inflater.inflate(R.layout.fragment_home, container, false)


//        val keretaButton: Button = view.findViewById(R.id.kereta_button)
//        keretaButton.setOnClickListener {
//            findNavController().navigate(R.id.action_homeFragment_to_pemesananKeretaLokal)
//        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButton(binding.keretaButton.root, R.drawable.ic_train, "Kereta")
        setupButton(binding.pesawatButton.root, R.drawable.ic_plane, "Pesawat")

        setupTujuanPopuler()
    }

    private fun setupButton(button: View, iconResId: Int, label: String) {
        val buttonIcon = button.findViewById<ImageView>(R.id.button_icon)
        val buttonLabel = button.findViewById<TextView>(R.id.button_label)
        buttonIcon.setImageResource(iconResId)
        buttonLabel.text = label
    }

    private fun setupTujuanPopuler() {
        val cardContainer = binding.tujuanPopulerContainer

        val card1 = layoutInflater.inflate(R.layout.item_tujuan_populer, cardContainer, false)
        setCardBackground(card1, R.drawable.jakarta)
        card1.findViewById<TextView>(R.id.card_text).text = "Jakarta"
        cardContainer.addView(card1)

        val card2 = layoutInflater.inflate(R.layout.item_tujuan_populer, cardContainer, false)
        setCardBackground(card2, R.drawable.surabaya)
        card2.findViewById<TextView>(R.id.card_text).text = "Surabaya"
        cardContainer.addView(card2)

        val card3 = layoutInflater.inflate(R.layout.item_tujuan_populer, cardContainer, false)
        setCardBackground(card3, R.drawable.yogyakarta)
        card3.findViewById<TextView>(R.id.card_text).text = "Yogyakarta"
        cardContainer.addView(card3)

        // Add more cards as needed
    }

    private fun setCardBackground(card: View, backgroundResId: Int) {
        val cardBackground = card.findViewById<RelativeLayout>(R.id.card_container)
        cardBackground.setBackgroundResource(backgroundResId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}