package com.example.value

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.value.Model.ValueDAO
import com.example.value.Model.ValueDB
import com.example.value.Model.ValueItem
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TestDao {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var mValueDao: ValueDAO
    private lateinit var db: ValueDB

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ValueDB::class.java).build()
        mValueDao = db.getValueDAO()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertListElements() = runBlocking {
        //given
        val valueList = listOf(ValueItem("USD",
                "Dolar Americano",
                "747,61"))

        // when
        mValueDao.insertAllValue(valueList)

        //then
        mValueDao.getAllValueFromDB().observeForever{
            assertThat(it).isNotNull()
            println(it.toString())
            assertThat(it[0].codigo).isEqualTo("USD")

        }
    }

}