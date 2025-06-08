//package com.example.projectakhirtis.network
//
//import com.example.uap_pam.Tanaman
//import retrofit2.Call
//import retrofit2.http.*
//
//interface ApiService {
//    @GET("plants")
//    fun getAllPlants(): Call<List<Tanaman>>
//
//    @GET("plants/{id}")
//    fun getPlantById(@Path("id") id: Int): Call<Tanaman>
//
//    @FormUrlEncoded
//    @POST("plants")
//    fun createPlant(
//        @Field("plant_name") plantName: String,
//        @Field("description") description: String,
//        @Field("price") price: String
//    ): Call<Tanaman>
//
//    @FormUrlEncoded
//    @PUT("plants/{id}")
//    fun updatePlant(
//        @Path("id") id: Int,
//        @Field("plant_name") plantName: String,
//        @Field("description") description: String,
//        @Field("price") price: String
//    ): Call<Tanaman>
//
//    @DELETE("plants/{id}")
//    fun deletePlant(@Path("id") id: Int): Call<Void>
//}
