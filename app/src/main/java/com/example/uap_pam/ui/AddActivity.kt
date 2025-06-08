package com.example.uap_pam.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uap_pam.databinding.ActivityAddBinding
import com.example.uap_pam.Tanaman
import com.example.uap_pam.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTambah.setOnClickListener {
            val name = binding.etNama.text.toString().trim()
            val desc = binding.etDeskripsi.text.toString().trim()
            val price = binding.etHarga.text.toString().trim()

            if (name.isEmpty() || desc.isEmpty() || price.isEmpty()) {
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newPlant = Tanaman(
                plant_name = name,
                description = desc,
                price = price
            )

            ApiClient.api.createPlant(newPlant).enqueue(object : Callback<Tanaman> {
                override fun onResponse(call: Call<Tanaman>, response: Response<Tanaman>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@AddActivity, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@AddActivity, "Gagal menambahkan data: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Tanaman>, t: Throwable) {
                    Toast.makeText(this@AddActivity, "Gagal: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
