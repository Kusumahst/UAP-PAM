package com.example.uap_pam.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.uap_pam.R
import com.example.uap_pam.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nama = intent.getStringExtra("nama") ?: "Tanaman"
        val harga = intent.getStringExtra("harga") ?: "-"
        val deskripsi = intent.getStringExtra("deskripsi") ?: "Tanaman ini berasal dari negara x, merupakan tanaman langka"
        val imageResId = intent.getIntExtra("imageResId", R.drawable.ic_tanaman)

        binding.tvNama.text = nama
        binding.tvHarga.text = harga
        binding.tvDeskripsi.text = deskripsi
        binding.imgTanaman.setImageResource(imageResId)

        binding.btnUpdate.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java).apply {
                putExtra("nama", nama)
                putExtra("harga", harga)
                putExtra("deskripsi", deskripsi)
                putExtra("imageResId", imageResId)
            }
            startActivity(intent)
        }
    }
}
