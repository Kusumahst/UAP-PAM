package com.example.uap_pam.network

import com.example.uap_pam.PlantListResponse
import com.example.uap_pam.Tanaman
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("plant/all")
    fun getAllPlants(): Call<PlantListResponse>

    @GET("plant/{plant_name}")
    fun getPlantByName(@Path("plant_name") plantName: String): Call<Tanaman>

    @POST("plant/new")
    fun createPlant(@Body plant: Tanaman): Call<Tanaman>

    @PUT("plant/{plant_name}")
    fun updatePlant(
        @Path("plant_name") plantName: String,
        @Body plant: Tanaman
    ): Call<Tanaman>

    @DELETE("plant/{plant_name}")
    fun deletePlant(@Path("plant_name") plantName: String): Call<Void>
}
