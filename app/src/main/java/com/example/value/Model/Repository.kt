package com.example.value.Model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.value.Retrofit.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val mValueDAO: ValueDAO) {

    private val service = RetrofitClient.getRetroValue()

    val mMoney = mValueDAO.getAllValueFromDB()

    val mDataValueDBList = mutableListOf<Value>()

    fun getTiempoFromServer() {
        Log.d("mas", "saludo")
        val mCall = service.getClimaFromApi()
        mCall.enqueue(object : Callback<List<ValueItem>> {
            override fun onResponse(
                call: Call<List<ValueItem>>,
                response: Response<List<ValueItem>>
            ) {
                Log.d("otro log", response.body().toString())
                when (response.code()) {

                    in 200..299 -> CoroutineScope(Dispatchers.IO).launch {
                        response.body()?.let {
                            Log.d("clima", it.toString())
                            mValueDAO.insertAllValue(it)

                        }
                    }
                    in 300..399 -> Log.d("ERROR 300 ", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<List<ValueItem>>, t: Throwable) {
                Log.d("error", t.message.toString())

            }

        })

    }

    fun getOneByCode(codigo: String): LiveData<ValueItem> {
        return mValueDAO.getCodeByID(codigo)
    }

    fun getOneByNombre(nombre: String): LiveData<ValueItem> {
        return mValueDAO.getNameByID(nombre)
    }

    fun getOneByValor(valor: String): LiveData<ValueItem> {
        return mValueDAO.getValueByID(valor)
    }
}