package com.example.value.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object{

        private const val URL_BASE = "https://api.gael.cl/general/public/"

        fun getRetroValue() : ApiValue {

            val mRetrofitClima = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return mRetrofitClima.create(ApiValue::class.java)
        }
    }
}