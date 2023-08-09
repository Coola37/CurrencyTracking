package com.example.currencytracking.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.currencytracking.model.Exchange

class FakeExchangeRepository : ExchangeRepositoryInterface {

     val exchange = mutableListOf<Exchange>()
     val exchangeLiveData = MutableLiveData<List<Exchange>>(exchange)


    override suspend fun insertExchange(currency: Exchange) {
        exchange.add(currency)
    }

    override suspend fun deleteExchange(currency: Exchange) {
        exchange.remove(currency)
    }

    override fun getExchange(): LiveData<List<Exchange>> {
        return exchangeLiveData
    }
}