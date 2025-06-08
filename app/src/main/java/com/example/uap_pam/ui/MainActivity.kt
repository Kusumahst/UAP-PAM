package com.example.uap_pam.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uap_pam.PlantListResponse
import com.example.uap_pam.adapter.TanamanAdapter
import com.example.uap_pam.databinding.ActivityMainBinding
import com.example.uap_pam.Tanaman
import com.example.uap_pam.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val tanamanList = mutableListOf<Tanaman>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.btnTambah.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }

        fetchData()
    }

    override fun onResume() {
        super.onResume()
        fetchData() // Refresh data setelah Add/Update
    }

    private fun fetchData() {
        ApiClient.api.getAllPlants().enqueue(object : Callback<PlantListResponse> {
            override fun onResponse(
                call: Call<PlantListResponse>,
                response: Response<PlantListResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val listTanaman = response.body()!!.data
                    if (listTanaman.isNotEmpty()) {
                        Toast.makeText(this@MainActivity, "Berhasil ambil data: ${listTanaman.size} item", Toast.LENGTH_SHORT).show()
                        binding.recyclerView.adapter = TanamanAdapter(
                            listTanaman,
                            onDelete = { tanaman -> confirmDelete(tanaman) },
                            onDetail = { tanaman -> openDetail(tanaman) }
                        )
                    } else {
                        Toast.makeText(this@MainActivity, "Data kosong", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Gagal: ${response.code()} - ${response.message()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<PlantListResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Network error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun confirmDelete(tanaman: Tanaman) {
        AlertDialog.Builder(this)
            .setTitle("Hapus Tanaman")
            .setMessage("Yakin ingin menghapus ${tanaman.plant_name}?")
            .setPositiveButton("Ya") { _, _ -> deleteTanaman(tanaman) }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun deleteTanaman(tanaman: Tanaman) {
        ApiClient.api.deletePlant(tanaman.plant_name).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Toast.makeText(this@MainActivity, "Data dihapus", Toast.LENGTH_SHORT).show()
                fetchData()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Gagal hapus: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun openDetail(tanaman: Tanaman) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("id", tanaman.id)
            putExtra("plant_name", tanaman.plant_name)
            putExtra("description", tanaman.description)
            putExtra("price", tanaman.price)
        }
        startActivity(intent)
    }
}
