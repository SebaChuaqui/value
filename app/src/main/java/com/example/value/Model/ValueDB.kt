package com.example.value.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DATA_BASE_NAME="value_db"

@Database(entities = [ValueItem::class], version = 1)

abstract class ValueDB : RoomDatabase() {

    abstract fun getValueDAO(): ValueDAO

    companion object {

        @Volatile
        private var INSTANCE: ValueDB? = null

        fun getDataBase(context: Context): ValueDB {

            val tempInterface = INSTANCE
            if (tempInterface != null) {
                return tempInterface
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    ValueDB::class.java,
                    DATA_BASE_NAME
                )
                    .build()
                INSTANCE = instance
                return instance

            }
        }
    }
}