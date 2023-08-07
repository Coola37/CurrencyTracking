package com.example.currencytracking.repo

import androidx.lifecycle.LiveData
import com.example.currencytracking.model.Exchange

interface ExchangeRepositoryInterface {
    suspend fun  insertExchange(currency: Exchange)

    suspend fun deleteExchange(currency: Exchange)

    fun getExchange(): LiveData<List<Exchange>>
}