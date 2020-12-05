package com.example.value.Model
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ValueDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllValue(mClimaList: List<ValueItem>)

    @Query("SELECT * FROM value_table")
    fun getAllValueFromDB(): LiveData<List<ValueItem>>

    @Query("SELECT * FROM value_table WHERE codigo=:mcodigo" )
    fun getCodeByID(mcodigo: String): LiveData<ValueItem>

    @Query("SELECT * FROM value_table WHERE nombre=:mnombre" )
    fun getNameByID(mnombre: String): LiveData<ValueItem>

    @Query("SELECT * FROM value_table WHERE valor=:mvalor" )
    fun getValueByID(mvalor: String): LiveData<ValueItem>

    @Update
    fun getUpdate(mValueList: List<ValueItem>)
}