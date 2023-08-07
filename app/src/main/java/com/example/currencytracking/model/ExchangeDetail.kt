package com.example.currencytracking.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ExchangeDetail(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "satis")
    @SerializedName("satis")
    val satis: String,

    @ColumnInfo(name = "alis")
    @SerializedName("alis")
    val alis: String,

    @ColumnInfo(name = "degisim")
    @SerializedName("degisim")
    val degisim: String,

    @ColumnInfo(name = "d_oran")
    @SerializedName("d_oran")
    val d_oran: String,

    @SerializedName("d_yon")
    @ColumnInfo(name = "d_yon") val d_yon: String
)

