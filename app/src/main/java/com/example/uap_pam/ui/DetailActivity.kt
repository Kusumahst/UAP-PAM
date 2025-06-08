package com.example.uap_pam.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.uap_pam.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("id", -1)
        val name = intent.getStringExtra("plant_name")
        val desc = intent.getStringExtra("description")
        val price = intent.getStringExtra("price")

        binding.tvNama.text = name
        binding.tvDeskripsi.text = desc
        binding.tvHarga.text = "Rp $price"

        binding.btnUpdate.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java).apply {
                putExtra("id", id)
                putExtra("plant_name", name)
                putExtra("description", desc)
                putExtra("price", price)
            }
            startActivity(intent)
        }
    }
}
