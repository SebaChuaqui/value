package com.example.value

import com.example.value.Model.ValueItem
import com.example.value.Retrofit.ApiValue
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiTest {

    lateinit var mMockWebServer: MockWebServer
    lateinit var mApiValue : ApiValue

    @Before
    fun setUp() {
        mMockWebServer = MockWebServer()
        val retro = Retrofit.Builder()
            .baseUrl(mMockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mApiValue =  retro.create(com.example.value.Retrofit.ApiValue::class.java)
    }

    @After
    fun shutDown() {
        mMockWebServer.shutdown()
    }

    @Test
    fun getAllDrugstore_happy_case() = runBlocking {
        //given
        val mresultList = listOf<ValueItem>(
            ValueItem("USD",
                "Dolar Americano",
                "747")
        )
        mMockWebServer.enqueue(MockResponse().setBody(Gson().toJson(mresultList)))

        //when
        val result = mApiValue.getDataFromApiCorutines()
        //then
        assertThat(result).isNotNull()
        assertThat(result.isSuccessful).isTrue()
        val message = result.body()
        assertThat(message).hasSize(1)
        println(message.toString())
        assertThat(message?.get(0)?.codigo?.contains("USD")).isTrue()
        val request = mMockWebServer.takeRequest()
        Truth.assertThat(request.path).isEqualTo("/monedas/")
    }


}