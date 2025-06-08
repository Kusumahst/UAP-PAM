package com.example.uap_pam.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.uap_pam.R
import com.example.uap_pam.Tanaman
import com.google.android.material.button.MaterialButton

class AddActivity : AppCompatActivity() {
    private lateinit var etNama: EditText
    private lateinit var etHarga: EditText
    private lateinit var etDeskripsi: EditText
    private lateinit var btnTambah: MaterialButton
    private lateinit var imgTanaman: ImageView

    private var selectedImageUri: Uri? = null // Opsional kalau nanti kamu mau ambil gambar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add) // Pastikan nama file XML-nya bener

        etNama = findViewById(R.id.etNama)
        etHarga = findViewById(R.id.etHarga)
        etDeskripsi = findViewById(R.id.etDeskripsi)
        btnTambah = findViewById(R.id.btnTambah)
        imgTanaman = findViewById(R.id.imgTanaman)

        btnTambah.setOnClickListener {
            val nama = etNama.text.toString().trim()
            val hargaStr = etHarga.text.toString().trim()
            val deskripsi = etDeskripsi.text.toString().trim()

            if (nama.isEmpty() || hargaStr.isEmpty() || deskripsi.isEmpty()) {
                Toast.makeText(this, "Isi semua data dulu ya!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val harga = hargaStr.toIntOrNull()
            if (harga == null) {
                Toast.makeText(this, "Harga harus berupa angka", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Simpan ke repository
            val tanaman = Tanaman(
                nama = nama,
                harga = harga,
                deskripsi = deskripsi,
                imageResId = null
            )

            TanamanRepository.tambahTanaman(tanaman)

            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}
