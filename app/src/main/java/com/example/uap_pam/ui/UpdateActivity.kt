package com.example.uap_pam.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uap_pam.databinding.ActivityUpdateBinding
import com.example.uap_pam.Tanaman
import com.example.uap_pam.network.ApiClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val plantName = intent.getStringExtra("plant_name") ?: ""
        val desc = intent.getStringExtra("description") ?: ""
        val price = intent.getStringExtra("price") ?: ""

        binding.etNama.setText(plantName)
        binding.etDeskripsi.setText(desc)
        binding.etHarga.setText(price)

        binding.btnSimpan.setOnClickListener {
            val updatedName = binding.etNama.text.toString()
            val updatedDesc = binding.etDeskripsi.text.toString()
            val updatedPrice = binding.etHarga.text.toString()

            val updatedPlant = Tanaman(
                plant_name = updatedName,
                description = updatedDesc,
                price = updatedPrice
            )

            ApiClient.api.updatePlant(plantName, updatedPlant)
                .enqueue(object : Callback<Tanaman> {
                    override fun onResponse(call: Call<Tanaman>, response: Response<Tanaman>)  {
                        if (response.isSuccessful) {
                            Toast.makeText(this@UpdateActivity, "Update berhasil", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(this@UpdateActivity, "Update gagal: ${response.message()}", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Tanaman>, t: Throwable) {
                        Toast.makeText(this@UpdateActivity, "Update gagal: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}
