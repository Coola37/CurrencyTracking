package com.example.currencytracking.model

import com.google.gson.annotations.SerializedName

data class ExchangeResponse(

    @SerializedName("USD")
    val usd: ExchangeDetail?,
    @SerializedName("EUR")
    val eur: ExchangeDetail?,
    @SerializedName("GBP")
    val gbp: ExchangeDetail?,
    @SerializedName("GA")
    val ga: ExchangeDetail?,
    @SerializedName("C")
    val c: ExchangeDetail?,
    @SerializedName("GAG")
    val gag: ExchangeDetail?,
    @SerializedName("BTC")
    val btc: ExchangeDetail?,
    @SerializedName("ETH")
    val eth: ExchangeDetail?,
    @SerializedName("XU100")
    val xu100: ExchangeDetail?,

)
