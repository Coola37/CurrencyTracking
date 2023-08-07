package com.example.currencytracking.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

@Entity
@TypeConverters(ExchangeTypeConverter::class)
data class Exchange(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "usd")
    @SerializedName("USD")
    val usd: ExchangeDetail?,

    @ColumnInfo(name = "eur")
    @SerializedName("EUR")
    val eur: ExchangeDetail?,

    @ColumnInfo(name = "gbp")
    @SerializedName("GBP")
    val gbp: ExchangeDetail?,

    @ColumnInfo(name = "ga")
    @SerializedName("GA")
    val ga: ExchangeDetail?,

    @ColumnInfo(name = "c")
    @SerializedName("C")
    val c: ExchangeDetail?,

    @ColumnInfo(name = "gag")
    @SerializedName("GAG")
    val gag: ExchangeDetail?,

    @ColumnInfo(name = "btc")
    @SerializedName("BTC")
    val btc: ExchangeDetail?,

    @ColumnInfo(name = "eth")
    @SerializedName("ETH")
    val eth: ExchangeDetail?,

    @ColumnInfo(name = "xu100")
    @SerializedName("XU100")
    val xu100: ExchangeDetail?
)