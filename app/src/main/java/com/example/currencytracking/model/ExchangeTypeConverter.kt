package com.example.currencytracking.model

import androidx.room.TypeConverter
import com.google.gson.Gson

class ExchangeTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): ExchangeDetail {
        return gson.fromJson(value, ExchangeDetail::class.java)
    }

    @TypeConverter
    fun toString(details: ExchangeDetail): String {
        return gson.toJson(details)
    }
}