package com.example.classpacket.IP

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface IPService {
    @GET ("http://api.ipstack.com/")
    fun getCurrentIPData(
        @Query("ip") ip: String,
        @Query("hostname") hostname: String,
        @Query("APPID") app_id: String
    ):
            Call<IPResponse>
}
