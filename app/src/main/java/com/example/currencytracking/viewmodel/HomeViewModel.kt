package com.example.currencytracking.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencytracking.model.Exchange
import com.example.currencytracking.repo.ExchangeRepositoryInterface
import com.example.currencytracking.retrofit.API
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ExchangeRepositoryInterface,
    private val retrofitAPI: API
) : ViewModel() {

    val exchangeData: LiveData<List<Exchange>> = repository.getExchange()

    // Retrofit çağrısı
    fun fetchExchangeData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = retrofitAPI.getExchangeData().execute()
                if (response.isSuccessful) {
                    val exchangeResponse = response.body()

                    if (exchangeResponse != null) {
                        val exchange = Exchange(
                            usd = exchangeResponse.usd,
                            eur = exchangeResponse.eur,
                            gbp = exchangeResponse.gbp,
                            ga = exchangeResponse.ga,
                            c = exchangeResponse.c,
                            gag = exchangeResponse.gag,
                            btc = exchangeResponse.btc,
                            eth = exchangeResponse.eth,
                            xu100 = exchangeResponse.xu100
                        )
                        repository.insertExchange(exchange)
                    } else {
                        Log.e("ExchangeResponse", "ExchangeResponse is null")
                    }
                } else {
                    Log.e("Response Error", "Response unsuccessful: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("Fetch Error", "Exception: $e")
                e.printStackTrace()
            }
        }
    }


}
