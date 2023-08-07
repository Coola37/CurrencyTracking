package com.example.currencytracking.retrofit

import com.example.currencytracking.model.Exchange
import com.example.currencytracking.utils.Util.END_POINT
import retrofit2.http.GET

interface API {

    @GET(END_POINT)
    fun getExchangeData(): Exchange
}