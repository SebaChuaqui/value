package com.example.value.Retrofit

import com.example.value.Model.ValueItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiValue {

    @GET("monedas/")
    fun getClimaFromApi(): retrofit2.Call<List<ValueItem>>

    @GET("monedas/")
    suspend fun getDataFromApiCorutines() : Response<List<ValueItem>>
}