package com.example.currencytracking.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currencytracking.model.Exchange
import com.example.currencytracking.repo.ExchangeRepositoryInterface
import com.example.currencytracking.retrofit.API
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConvertViewModel @Inject constructor(
    private val repository : ExchangeRepositoryInterface,
    private val retrofitAPI : API,
    application: Application
) : BaseViewModel(application){

    val exchangeData: LiveData<List<Exchange>> = repository.getExchange()

    fun fetchExchangeData() {
        launch {
            try {
                val response = retrofitAPI.getExchangeData().execute()
                if (response.isSuccessful) {
                    response.body()?.let { exchangeResponse ->
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
                    } ?: run {
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