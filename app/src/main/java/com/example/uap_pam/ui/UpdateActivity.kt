package com.example.uap_pam.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.uap_pam.R
import com.example.uap_pam.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nama = intent.getStringExtra("nama") ?: ""
        val harga = intent.getStringExtra("harga") ?: ""
        val deskripsi = intent.getStringExtra("deskripsi") ?: ""
        val imageResId = intent.getIntExtra("imageResId", R.drawable.ic_tanaman)

        binding.etNama.setText(nama)
        binding.etHarga.setText(harga)
        binding.etDeskripsi.setText(deskripsi)
        binding.imgTanaman.setImageResource(imageResId)

        binding.btnSimpan.setOnClickListener {
            Toast.makeText(this, "Berhasil disimpan!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
