package com.example.uap_pam

data class Tanaman(
    val id: Int? = null,
    val plant_name: String,
    val description: String,
    val price: String,
    val created_at: String? = null,
    val updated_at: String? = null
)

data class PlantListResponse(
    val message: String,
    val data: List<Tanaman>
)

