package com.example.currencytracking.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.currencytracking.model.Exchange

@Dao
interface ExchangeDao {

    @Insert
    suspend fun  insertExhange(exchange: Exchange)

    @Delete
    suspend fun deleteExchange(exchange: Exchange)

    @Query(value = "SELECT * FROM Exchange")
    fun observeExchange() : LiveData<List<Exchange>>
}