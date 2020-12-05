package com.example.value.Model
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "value_table")
data class ValueItem(
    @PrimaryKey
    @SerializedName("Codigo")
    val codigo: String,
    @SerializedName("Nombre")
    val nombre: String,
    @SerializedName("Valor")
    val valor: String
)