package com.example.uap_pam.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uap_pam.Tanaman
import com.example.uap_pam.R
import com.example.uap_pam.TanamanAdapter
import com.example.uap_pam.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TanamanAdapter
    private lateinit var auth: FirebaseAuth

    private val plantList = mutableListOf(
        Tanaman("Daun Hijau", "Rp 200.000", R.drawable.ic_tanaman),
        Tanaman("Aglonema", "Rp 150.000", R.drawable.ic_tanaman)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        setupRecyclerView()

        binding.btnTambah.setOnClickListener {
            // Tambah dummy item (bisa diubah ke input form nanti)
            val newItem = Tanaman("Tanaman Baru", "Rp 100.000", R.drawable.ic_tanaman)
            plantList.add(newItem)
            adapter.notifyItemInserted(plantList.size - 1)
        }
    }

    private fun setupRecyclerView() {
        adapter = TanamanAdapter(
            items = plantList,
            onDelete = { position ->
                plantList.removeAt(position)
                adapter.notifyItemRemoved(position)
            },
            onDetail = { item ->
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra("nama", item.nama)
                    putExtra("harga", item.harga)
                    putExtra("imageResId", item.imageResId)
                }
                startActivity(intent)
            }
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}