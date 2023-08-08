package com.example.currencytracking.retrofit

import com.example.currencytracking.model.ExchangeResponse
import retrofit2.Call
import retrofit2.http.GET

interface API {

    @GET("embed/para-birimleri.json")
    fun getExchangeData(): Call<ExchangeResponse>
}