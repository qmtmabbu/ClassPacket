package com.example.classpacket.IP

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface IPService {
    @GET ("https://ipapi.co/json/")
    fun getCurrentIPData(
        @Query("ip") ip: String,
        @Query("city") city: String,
        @Query("region") region: String,
        @Query("country") country: String,
        @Query("continent_code") continent: String,
        @Query("postal") postal: String,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Query("AppId") AppId: String
    ):
            Call<IPResponse>
}
