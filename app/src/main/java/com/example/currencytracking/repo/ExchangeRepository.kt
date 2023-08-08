package com.example.currencytracking.repo

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.currencytracking.model.Exchange
import com.example.currencytracking.retrofit.API
import com.example.currencytracking.room.ExchangeDao
import javax.inject.Inject

class ExchangeRepository @Inject constructor(
    private val exchangeDao: ExchangeDao,
    private val retrofitAPI: API
):  ExchangeRepositoryInterface{
    override suspend fun insertExchange(exchange: Exchange) {
        exchangeDao.insertExhange(exchange)
    }

    override suspend fun deleteExchange(exchange: Exchange) {
        exchangeDao.deleteExchange(exchange)
    }

    override fun getExchange(): LiveData<List<Exchange>> {
        return exchangeDao.observeExchange()
    }
}